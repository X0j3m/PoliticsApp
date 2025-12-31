import {Component, inject, OnInit} from '@angular/core';
import {MemberService} from '../../service/member/member';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {v4 as uuid} from 'uuid';
import {MemberType} from '../../types/member.type';
import {Router, RouterLink} from '@angular/router';
import {catchError, of, tap} from 'rxjs';


@Component({
  selector: 'app-member-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './member-form.html',
  styleUrl: './member-form.css',
})
export class MemberForm implements OnInit {
  membersService = inject(MemberService)
  memberForm: FormGroup = new FormGroup({
    id: new FormControl(uuid()),
    name: new FormControl(''),
    surname: new FormControl(''),
    dateOfBirth: new FormControl(''),
    placeOfBirth: new FormControl(''),
    constituency: new FormControl(''),
    politicalPartyId: new FormControl('')
  })

  memberId: string = '';
  partyId: string = '';

  response: MemberType | null = null;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    const currentPath = this.router.url;
    if (currentPath.includes('edit')) {
      this.memberId = currentPath.split('/')[4];
      this.partyId = currentPath.split('/')[2];
      if (this.partyId && this.memberId) {
        this.loadMember();
      }
      this.memberForm.get('id')?.disable();
    } else {
      this.partyId = currentPath.split('/')[2];
      this.memberId = uuid()
    }
    this.memberForm.get('politicalPartyId')?.disable({ emitEvent: false });
    this.memberForm.get('politicalPartyId')?.setValue(this.partyId);
  }

  private loadMember(): void {
    this.membersService.get<MemberType>(this.partyId, this.memberId)
      .pipe(
        tap(response => {
          this.response = response;
          this.patchFormValues(response);
        }),
        catchError(error => {
          console.error('Błąd podczas ładowania członka:', error);
          this.router.navigate([`/political-parties/${this.partyId}/members`], {
            queryParams: {error: 'Nie znaleziono członka'}
          });
          return of(null);
        }),
      )
      .subscribe();
  }

  private patchFormValues(member: MemberType): void {
    this.memberForm.patchValue({
      id: member.id,
      name: member.name,
      surname: member.surname,
      dateOfBirth: member.dateOfBirth,
      placeOfBirth: member.placeOfBirth,
      constituency: member.constituency,
      politicalPartyId: member.politicalPartyId
    });
  }

  protected submitForm() {
    let id = this.memberId;
    let name = this.memberForm.value.name;
    let surname = this.memberForm.value.surname;
    let dateOfBirth = this.memberForm.value.dateOfBirth;
    let placeOfBirth = this.memberForm.value.placeOfBirth;
    let constituency = this.memberForm.value.constituency;
    let politicalPartyId = this.partyId;

    console.log(id, name, surname, dateOfBirth, placeOfBirth, constituency, politicalPartyId);

    if (id === '' || name === '' || surname === '' || dateOfBirth === '' || placeOfBirth === '' || constituency === '' || politicalPartyId === '') {
      return;
    }
    console.log("jest git")

    let request: MemberType = {
      id: id,
      name: name,
      surname: surname,
      dateOfBirth: dateOfBirth,
      placeOfBirth: placeOfBirth,
      constituency: constituency,
      politicalPartyId: politicalPartyId
    }

    console.log(request)

    this.membersService.create(request).subscribe();
    this.router.navigate([`/political-parties/${this.partyId}`]);

  }
}
