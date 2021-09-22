import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICvIstruzione, CvIstruzione } from 'app/shared/model/cv-istruzione.model';
import { CvIstruzioneService } from './cv-istruzione.service';
import { CvIstruzioneComponent } from './cv-istruzione.component';
import { CvIstruzioneDetailComponent } from './cv-istruzione-detail.component';
import { CvIstruzioneUpdateComponent } from './cv-istruzione-update.component';

@Injectable({ providedIn: 'root' })
export class CvIstruzioneResolve implements Resolve<ICvIstruzione> {
  constructor(private service: CvIstruzioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICvIstruzione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cvIstruzione: HttpResponse<CvIstruzione>) => {
          if (cvIstruzione.body) {
            return of(cvIstruzione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CvIstruzione());
  }
}

export const cvIstruzioneRoute: Routes = [
  {
    path: '',
    component: CvIstruzioneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cvIstruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CvIstruzioneDetailComponent,
    resolve: {
      cvIstruzione: CvIstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cvIstruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CvIstruzioneUpdateComponent,
    resolve: {
      cvIstruzione: CvIstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cvIstruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CvIstruzioneUpdateComponent,
    resolve: {
      cvIstruzione: CvIstruzioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cvIstruzione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
