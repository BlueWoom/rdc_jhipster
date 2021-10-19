import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICv, Cv } from 'app/shared/model/cv.model';
import { CvService } from './cv.service';
import { CvComponent } from './cv.component';
import { CvDetailComponent } from './cv-detail.component';
import { CvUpdateComponent } from './cv-update.component';

@Injectable({ providedIn: 'root' })
export class CvResolve implements Resolve<ICv> {
  constructor(private service: CvService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICv> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cv: HttpResponse<Cv>) => {
          if (cv.body) {
            return of(cv.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cv());
  }
}

export const cvRoute: Routes = [
  {
    path: '',
    component: CvComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rdcJhipsterApp.cv.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CvDetailComponent,
    resolve: {
      cv: CvResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cv.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CvUpdateComponent,
    resolve: {
      cv: CvResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cv.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CvUpdateComponent,
    resolve: {
      cv: CvResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.cv.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
