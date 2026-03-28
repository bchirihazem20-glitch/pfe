import { CanActivateFn } from '@angular/router';

export const adminFileGuard: CanActivateFn = (route, state) => {
  return true;
};
