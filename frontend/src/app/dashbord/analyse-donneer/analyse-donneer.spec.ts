import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseDonneer } from './analyse-donneer';

describe('AnalyseDonneer', () => {
  let component: AnalyseDonneer;
  let fixture: ComponentFixture<AnalyseDonneer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalyseDonneer],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyseDonneer);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
