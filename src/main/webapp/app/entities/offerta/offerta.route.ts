import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferta, Offerta } from 'app/shared/model/offerta.model';
import { OffertaService } from './offerta.service';
import { OffertaComponent } from './offerta.component';
import { OffertaDetailComponent } from './offerta-detail.component';
import { OffertaUpdateComponent } from './offerta-update.component';

@Injectable({ providedIn: 'root' })
export class OffertaResolve implements Resolve<IOfferta> {
  constructor(private service: OffertaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferta> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerta: HttpResponse<Offerta>) => {
          if (offerta.body) {
            return of(offerta.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Offerta());
  }
}

export const offertaRoute: Routes = [
  {
    path: '',
    component: OffertaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rdcJhipsterApp.offerta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OffertaDetailComponent,
    resolve: {
      offerta: OffertaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offerta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OffertaUpdateComponent,
    resolve: {
      offerta: OffertaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offerta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OffertaUpdateComponent,
    resolve: {
      offerta: OffertaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offerta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
