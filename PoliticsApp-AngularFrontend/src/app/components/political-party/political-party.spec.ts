import { ComponentFixture, TestBed } from '@angular/core/testing';

import PoliticalParty from './political-party';

describe('PoliticalParty', () => {
  let component: PoliticalParty;
  let fixture: ComponentFixture<PoliticalParty>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoliticalParty]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoliticalParty);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
