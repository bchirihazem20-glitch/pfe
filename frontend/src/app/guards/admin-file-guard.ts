import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth/auth';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

export const adminFileGuard: CanActivateFn = () => {
  const router = inject(Router);
  const authService = inject(AuthService);

  return authService.getProfile().pipe(
    map((user: any) => {
      if (user?.role === 'ADMIN') {
        return true;
      }

      return router.createUrlTree(['/']);
    }),
    catchError(() => of(router.createUrlTree(['/login'])))
  );
};