import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIstruzione, Istruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from './istruzione.service';
import { IstruzioneComponent } from './istruzione.component';
import { IstruzioneDetailComponent } from './istruzione-detail.component';
import { IstruzioneUpdateComponent } from './istruzione-update.component';

@Injectable({ providedIn: 'root' })
export class IstruzioneResolve implements Resolve<IIstruzione> {
  constructor(private service: IstruzioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIstruzione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((istruzione: HttpResponse<Istruzione>) => {
          if (istruzione.body) {
            return of(istruzione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Istruzione());
  }
}

export const istruzioneRoute: Routes = [
  {
    path: '',
    component: IstruzioneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.istruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IstruzioneDetailComponent,
    resolve: {
      istruzione: IstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.istruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IstruzioneUpdateComponent,
    resolve: {
      istruzione: IstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.istruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IstruzioneUpdateComponent,
    resolve: {
      istruzione: IstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.istruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
