import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEsperienza, Esperienza } from 'app/shared/model/esperienza.model';
import { EsperienzaService } from './esperienza.service';
import { EsperienzaComponent } from './esperienza.component';
import { EsperienzaDetailComponent } from './esperienza-detail.component';
import { EsperienzaUpdateComponent } from './esperienza-update.component';

@Injectable({ providedIn: 'root' })
export class EsperienzaResolve implements Resolve<IEsperienza> {
  constructor(private service: EsperienzaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEsperienza> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((esperienza: HttpResponse<Esperienza>) => {
          if (esperienza.body) {
            return of(esperienza.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Esperienza());
  }
}

export const esperienzaRoute: Routes = [
  {
    path: '',
    component: EsperienzaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rdcJhipsterApp.esperienza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EsperienzaDetailComponent,
    resolve: {
      esperienza: EsperienzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.esperienza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EsperienzaUpdateComponent,
    resolve: {
      esperienza: EsperienzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.esperienza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EsperienzaUpdateComponent,
    resolve: {
      esperienza: EsperienzaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.esperienza.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
