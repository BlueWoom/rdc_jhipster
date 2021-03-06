import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISkill, Skill } from 'app/shared/model/skill.model';
import { SkillService } from './skill.service';
import { SkillComponent } from './skill.component';
import { SkillDetailComponent } from './skill-detail.component';
import { SkillUpdateComponent } from './skill-update.component';

@Injectable({ providedIn: 'root' })
export class SkillResolve implements Resolve<ISkill> {
  constructor(private service: SkillService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISkill> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((skill: HttpResponse<Skill>) => {
          if (skill.body) {
            return of(skill.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Skill());
  }
}

export const skillRoute: Routes = [
  {
    path: '',
    component: SkillComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'rdcJhipsterApp.skill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SkillDetailComponent,
    resolve: {
      skill: SkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.skill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SkillUpdateComponent,
    resolve: {
      skill: SkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.skill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SkillUpdateComponent,
    resolve: {
      skill: SkillResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcJhipsterApp.skill.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
