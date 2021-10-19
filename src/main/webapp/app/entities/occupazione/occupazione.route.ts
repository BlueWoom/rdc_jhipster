import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOccupazione, Occupazione } from 'app/shared/model/occupazione.model';
import { OccupazioneService } from './occupazione.service';
import { OccupazioneComponent } from './occupazione.component';
import { OccupazioneDetailComponent } from './occupazione-detail.component';
import { OccupazioneUpdateComponent } from './occupazione-update.component';

@Injectable({ providedIn: 'root' })
export class OccupazioneResolve implements Resolve<IOccupazione> {
  constructor(private service: OccupazioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOccupazione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((occupazione: HttpResponse<Occupazione>) => {
          if (occupazione.body) {
            return of(occupazione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Occupazione());
  }
}

export const occupazioneRoute: Routes = [
  {
    path: '',
    component: OccupazioneComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rdcJhipsterApp.occupazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OccupazioneDetailComponent,
    resolve: {
      occupazione: OccupazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.occupazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OccupazioneUpdateComponent,
    resolve: {
      occupazione: OccupazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.occupazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OccupazioneUpdateComponent,
    resolve: {
      occupazione: OccupazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.occupazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
