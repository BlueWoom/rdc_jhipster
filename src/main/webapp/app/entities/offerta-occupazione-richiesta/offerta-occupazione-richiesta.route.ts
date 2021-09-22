import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOffertaOccupazioneRichiesta, OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { OffertaOccupazioneRichiestaService } from './offerta-occupazione-richiesta.service';
import { OffertaOccupazioneRichiestaComponent } from './offerta-occupazione-richiesta.component';
import { OffertaOccupazioneRichiestaDetailComponent } from './offerta-occupazione-richiesta-detail.component';
import { OffertaOccupazioneRichiestaUpdateComponent } from './offerta-occupazione-richiesta-update.component';

@Injectable({ providedIn: 'root' })
export class OffertaOccupazioneRichiestaResolve implements Resolve<IOffertaOccupazioneRichiesta> {
  constructor(private service: OffertaOccupazioneRichiestaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOffertaOccupazioneRichiesta> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offertaOccupazioneRichiesta: HttpResponse<OffertaOccupazioneRichiesta>) => {
          if (offertaOccupazioneRichiesta.body) {
            return of(offertaOccupazioneRichiesta.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OffertaOccupazioneRichiesta());
  }
}

export const offertaOccupazioneRichiestaRoute: Routes = [
  {
    path: '',
    component: OffertaOccupazioneRichiestaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.offertaOccupazioneRichiesta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OffertaOccupazioneRichiestaDetailComponent,
    resolve: {
      offertaOccupazioneRichiesta: OffertaOccupazioneRichiestaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.offertaOccupazioneRichiesta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OffertaOccupazioneRichiestaUpdateComponent,
    resolve: {
      offertaOccupazioneRichiesta: OffertaOccupazioneRichiestaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.offertaOccupazioneRichiesta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OffertaOccupazioneRichiestaUpdateComponent,
    resolve: {
      offertaOccupazioneRichiesta: OffertaOccupazioneRichiestaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.offertaOccupazioneRichiesta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
