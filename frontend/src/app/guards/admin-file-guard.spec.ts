import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { adminFileGuard } from './admin-file-guard';

describe('adminFileGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
    TestBed.runInInjectionContext(() => adminFileGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
