import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoliticalParties } from './political-parties';

describe('PoliticalParty', () => {
  let component: PoliticalParties;
  let fixture: ComponentFixture<PoliticalParties>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoliticalParties]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoliticalParties);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
