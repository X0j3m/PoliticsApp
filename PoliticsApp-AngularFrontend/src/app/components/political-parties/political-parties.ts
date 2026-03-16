import {Component, inject, OnChanges, OnInit, signal, SimpleChanges} from '@angular/core';
import {PoliticalPartyService} from '../../service/political-party/political-party';
import {PoliticalPartyType} from '../../types/political-party.type';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-political-parties',
  imports: [
    RouterLink,
  ],
  templateUrl: './political-parties.html',
  styleUrl: './political-parties.css',
})
export class PoliticalParties implements OnInit, OnChanges {
  politicalPartyService = inject(PoliticalPartyService)
  politicalParties = signal<Array<PoliticalPartyType>>([]);

  ngOnInit() {
    this.setPoliticalParties();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.setPoliticalParties();
  }

  setPoliticalParties(){
    this.politicalPartyService
      .getAll<PoliticalPartyType[]>()
      .subscribe(parties => this.politicalParties.set(parties));
  }

  delete(partyId: string) {
    this.politicalPartyService.delete(partyId).subscribe({
      next: () => {
        this.politicalParties.update(parties =>
          parties.filter(p => p.id !== partyId)
        );
      },
      error: error => console.error(error)
    });
  }
}
