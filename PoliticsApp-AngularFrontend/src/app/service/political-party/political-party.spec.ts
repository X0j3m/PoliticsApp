import { TestBed } from '@angular/core/testing';

import { PoliticalPartyService } from './political-party';

describe('PoliticalPartyService', () => {
  let service: PoliticalPartyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PoliticalPartyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
