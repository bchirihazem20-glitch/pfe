import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Entraineur } from './entraineur';

describe('Entraineur', () => {
  let component: Entraineur;
  let fixture: ComponentFixture<Entraineur>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Entraineur],
    }).compileComponents();

    fixture = TestBed.createComponent(Entraineur);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
