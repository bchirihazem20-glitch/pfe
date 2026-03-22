import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Inscripition } from './inscripition';

describe('Inscripition', () => {
  let component: Inscripition;
  let fixture: ComponentFixture<Inscripition>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Inscripition],
    }).compileComponents();

    fixture = TestBed.createComponent(Inscripition);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
