import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAzienda, Azienda } from 'app/shared/model/azienda.model';
import { AziendaService } from './azienda.service';
import { AziendaComponent } from './azienda.component';
import { AziendaDetailComponent } from './azienda-detail.component';
import { AziendaUpdateComponent } from './azienda-update.component';

@Injectable({ providedIn: 'root' })
export class AziendaResolve implements Resolve<IAzienda> {
  constructor(private service: AziendaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAzienda> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((azienda: HttpResponse<Azienda>) => {
          if (azienda.body) {
            return of(azienda.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Azienda());
  }
}

export const aziendaRoute: Routes = [
  {
    path: '',
    component: AziendaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.azienda.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AziendaDetailComponent,
    resolve: {
      azienda: AziendaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.azienda.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AziendaUpdateComponent,
    resolve: {
      azienda: AziendaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.azienda.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AziendaUpdateComponent,
    resolve: {
      azienda: AziendaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.azienda.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
