import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoliticalPartyForm } from './political-party-form';

describe('PoliticalPartyForm', () => {
  let component: PoliticalPartyForm;
  let fixture: ComponentFixture<PoliticalPartyForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoliticalPartyForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoliticalPartyForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
