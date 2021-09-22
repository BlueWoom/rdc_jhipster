import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INavigator, Navigator } from 'app/shared/model/navigator.model';
import { NavigatorService } from './navigator.service';
import { NavigatorComponent } from './navigator.component';
import { NavigatorDetailComponent } from './navigator-detail.component';
import { NavigatorUpdateComponent } from './navigator-update.component';

@Injectable({ providedIn: 'root' })
export class NavigatorResolve implements Resolve<INavigator> {
  constructor(private service: NavigatorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INavigator> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((navigator: HttpResponse<Navigator>) => {
          if (navigator.body) {
            return of(navigator.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Navigator());
  }
}

export const navigatorRoute: Routes = [
  {
    path: '',
    component: NavigatorComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.navigator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NavigatorDetailComponent,
    resolve: {
      navigator: NavigatorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.navigator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NavigatorUpdateComponent,
    resolve: {
      navigator: NavigatorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.navigator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NavigatorUpdateComponent,
    resolve: {
      navigator: NavigatorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.navigator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
