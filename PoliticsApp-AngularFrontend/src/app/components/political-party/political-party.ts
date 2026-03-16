import {Component, inject, OnInit, signal} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {PoliticalPartyService} from '../../service/political-party/political-party';
import {PoliticalPartyType} from '../../types/political-party.type';
import {Members} from '../members/members';

@Component({
  selector: 'app-political-party',
  imports: [
    RouterLink,
    Members
  ],
  templateUrl: './political-party.html',
  styleUrl: './political-party.css',
})
export class PoliticalParty implements OnInit {
  politicalPartyService = inject(PoliticalPartyService)
  politicalParty = signal<PoliticalPartyType>(
    {id: '', name: '', dateOfEstablishment: ''}
  );

  constructor(private router: Router) {}


  ngOnInit(): void {
    const currentPath = this.router.url;
    const id = currentPath.split('/')[2];

    this.politicalPartyService
      .get<PoliticalParty>(id)
      .subscribe(party =>
        this.politicalParty.set(party));
  }
}
