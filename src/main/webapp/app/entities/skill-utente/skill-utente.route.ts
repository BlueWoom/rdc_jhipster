import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISkillUtente, SkillUtente } from 'app/shared/model/skill-utente.model';
import { SkillUtenteService } from './skill-utente.service';
import { SkillUtenteComponent } from './skill-utente.component';
import { SkillUtenteDetailComponent } from './skill-utente-detail.component';
import { SkillUtenteUpdateComponent } from './skill-utente-update.component';

@Injectable({ providedIn: 'root' })
export class SkillUtenteResolve implements Resolve<ISkillUtente> {
  constructor(private service: SkillUtenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISkillUtente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((skillUtente: HttpResponse<SkillUtente>) => {
          if (skillUtente.body) {
            return of(skillUtente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SkillUtente());
  }
}

export const skillUtenteRoute: Routes = [
  {
    path: '',
    component: SkillUtenteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.skillUtente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SkillUtenteDetailComponent,
    resolve: {
      skillUtente: SkillUtenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.skillUtente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SkillUtenteUpdateComponent,
    resolve: {
      skillUtente: SkillUtenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.skillUtente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SkillUtenteUpdateComponent,
    resolve: {
      skillUtente: SkillUtenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.skillUtente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
