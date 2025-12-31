import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {PoliticalPartyService} from '../../service/political-party/political-party';
import {PoliticalPartyType} from '../../types/political-party.type';
import {Router, RouterLink} from '@angular/router';
import {v4 as uuid} from 'uuid';
import {catchError, of, tap} from 'rxjs';

@Component({
  selector: 'app-political-party-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './political-party-form.html',
  styleUrl: './political-party-form.css',
})
export class PoliticalPartyForm implements OnInit {
  politicalPartyService = inject(PoliticalPartyService)
  politicalPartyForm: FormGroup = new FormGroup({
    id: new FormControl(uuid()),
    name: new FormControl(''),
    dateOfEstablishment: new FormControl('')
  });
  partyId: string = '';
  response: PoliticalPartyType | null = null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    const currentPath = this.router.url;
    if (currentPath.includes('edit')) {
      const id = currentPath.split('/')[3];
      if (id) {
        this.loadPoliticalParty(id);
      }
      this.partyId = id;
      this.politicalPartyForm.get('id')?.disable();
    } else {
      this.partyId = uuid();
      console.log("Tworzenie partii");
    }
  }

  private loadPoliticalParty(id: string): void {
    this.politicalPartyService.get<PoliticalPartyType>(id)
      .pipe(
        tap(response => {
          this.response = response;
          this.patchFormValues(response);
        }),
        catchError(error => {
          console.error('Błąd podczas ładowania partii:', error);
          this.router.navigate(['/political-parties'], {
            queryParams: { error: 'Nie znaleziono partii' }
          });
          return of(null);
        }),
      )
      .subscribe();
  }

  private patchFormValues(party: PoliticalPartyType): void {
    this.politicalPartyForm.patchValue({
      id: party.id,
      name: party.name,
      dateOfEstablishment: party.dateOfEstablishment
        ? new Date(party.dateOfEstablishment).toISOString().split('T')[0]
        : ''
    });
  }

  protected submitForm() {
    let id = this.partyId;
    let name = this.politicalPartyForm.value.name ?? '';
    let dateOfEstablishment = this.politicalPartyForm.value.dateOfEstablishment ?? '';

    if (id === '' || name === '' || dateOfEstablishment === '') {
      console.log(id, name, dateOfEstablishment)
      return;
    }

    let request: PoliticalPartyType = {
      id: id,
      name: name,
      dateOfEstablishment: dateOfEstablishment
    }

    console.log(request)
    this.politicalPartyService.create(request).subscribe();
    this.router.navigate(['/political-parties']);
  }
}
