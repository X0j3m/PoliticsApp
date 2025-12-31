import {Component, inject, OnInit, signal} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {MemberService} from '../../service/member/member';
import {MemberType} from '../../types/member.type';
import {PoliticalPartyType} from '../../types/political-party.type';
import {PoliticalPartyService} from '../../service/political-party/political-party';

@Component({
  selector: 'app-member',
  imports: [
    RouterLink
  ],
  templateUrl: './member.html',
  styleUrl: './member.css',
})
export class Member implements OnInit {
  membersService = inject(MemberService)
  politicalPartiesService = inject(PoliticalPartyService)
  member = signal<MemberType>(
    {
      id: '',
      name: '',
      surname: '',
      dateOfBirth: '',
      placeOfBirth: '',
      constituency: 0,
      politicalPartyId: ''
    });
  politicalParty = signal<PoliticalPartyType>(
    {id: '', name: '', dateOfEstablishment: ''}
  );

  constructor(private router: Router) {}

  ngOnInit(): void {
    const currentPath = this.router.url;
    const pathElements = currentPath.split('/');
    const id = pathElements[4];
    const partyId = pathElements[2];

    this.membersService
      .get<MemberType>(partyId, id)
      .subscribe(
        member => this.member.set(member)
      )

    this.politicalPartiesService
      .get<PoliticalPartyType>(partyId)
      .subscribe(
        party => this.politicalParty.set(party)
      )
  }
}
