import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOffertaSkill, OffertaSkill } from 'app/shared/model/offerta-skill.model';
import { OffertaSkillService } from './offerta-skill.service';
import { OffertaSkillComponent } from './offerta-skill.component';
import { OffertaSkillDetailComponent } from './offerta-skill-detail.component';
import { OffertaSkillUpdateComponent } from './offerta-skill-update.component';

@Injectable({ providedIn: 'root' })
export class OffertaSkillResolve implements Resolve<IOffertaSkill> {
  constructor(private service: OffertaSkillService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOffertaSkill> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offertaSkill: HttpResponse<OffertaSkill>) => {
          if (offertaSkill.body) {
            return of(offertaSkill.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OffertaSkill());
  }
}

export const offertaSkillRoute: Routes = [
  {
    path: '',
    component: OffertaSkillComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offertaSkill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OffertaSkillDetailComponent,
    resolve: {
      offertaSkill: OffertaSkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offertaSkill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OffertaSkillUpdateComponent,
    resolve: {
      offertaSkill: OffertaSkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offertaSkill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OffertaSkillUpdateComponent,
    resolve: {
      offertaSkill: OffertaSkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.offertaSkill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
