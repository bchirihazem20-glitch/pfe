import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dashboardboutique } from './dashboardboutique';

describe('Dashboardboutique', () => {
  let component: Dashboardboutique;
  let fixture: ComponentFixture<Dashboardboutique>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Dashboardboutique],
    }).compileComponents();

    fixture = TestBed.createComponent(Dashboardboutique);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
