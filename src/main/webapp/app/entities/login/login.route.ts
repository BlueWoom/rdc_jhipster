import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILogin, Login } from 'app/shared/model/login.model';
import { LoginService } from './login.service';
import { LoginComponent } from './login.component';
import { LoginDetailComponent } from './login-detail.component';
import { LoginUpdateComponent } from './login-update.component';

@Injectable({ providedIn: 'root' })
export class LoginResolve implements Resolve<ILogin> {
  constructor(private service: LoginService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILogin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((login: HttpResponse<Login>) => {
          if (login.body) {
            return of(login.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Login());
  }
}

export const loginRoute: Routes = [
  {
    path: '',
    component: LoginComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.login.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LoginDetailComponent,
    resolve: {
      login: LoginResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.login.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LoginUpdateComponent,
    resolve: {
      login: LoginResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.login.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LoginUpdateComponent,
    resolve: {
      login: LoginResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.login.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
