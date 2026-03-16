import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadComponent: () => {
      return import("./components/political-parties/political-parties").then(m => m.PoliticalParties)
    }
  },
  {
    path: 'political-parties',
    loadComponent: () => {
      return import("./components/political-parties/political-parties").then(m => m.PoliticalParties)
    }
  },
  {
    path: 'political-parties/edit/:partyId',
    loadComponent: () => {
      return import("./components/political-party-form/political-party-form").then(m => m.PoliticalPartyForm)
    }
  },
  {
    path: 'political-parties/create',
    loadComponent: () => {
      return import("./components/political-party-form/political-party-form").then(m => m.PoliticalPartyForm)
    }
  },
  {
    path: 'political-parties/:partyId',
    loadComponent: () => {
      return import("./components/political-party/political-party").then(m => m.PoliticalParty)
    }
  },
  {
    path: 'political-parties/:partyId/members/create',
    loadComponent: () => {
      return import("./components/member-form/member-form").then(m => m.MemberForm)
    }
  },
  {
    path: 'political-parties/:partyId/members/:memberId',
    loadComponent: () => {
      return import("./components/member/member").then(m => m.Member)
    }
  },
  {
    path: 'political-parties/:partyId/members/:memberId/edit',
    loadComponent: () => {
      return import("./components/member-form/member-form").then(m => m.MemberForm)
    }
  }
];

