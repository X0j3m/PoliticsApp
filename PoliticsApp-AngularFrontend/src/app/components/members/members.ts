import {Component, inject, Input, OnChanges, OnInit, signal, SimpleChanges} from '@angular/core';
import {MemberService} from '../../service/member/member';
import {MemberType} from '../../types/member.type';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-members',
  imports: [
    RouterLink
  ],
  templateUrl: './members.html',
  styleUrl: './members.css',
})
export class Members implements OnInit, OnChanges {
  membersService = inject(MemberService)
  members = signal<Array<MemberType>>([]);
  @Input() partyId!: string;

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.partyId) {
      this.membersService.getByPartyId<Array<MemberType>>(this.partyId)
        .subscribe(members => this.members.set(members));
    }
  }

  protected delete(partyId: string, memberId: string) {
    this.membersService.delete(partyId, memberId).subscribe({
      next: () => {
        this.members.update(members =>
          members.filter(m => m.id !== memberId)
        );
      },
      error: error => console.error(error)
    });
  }
}
