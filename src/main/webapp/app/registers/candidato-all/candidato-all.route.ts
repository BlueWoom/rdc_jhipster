import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Route, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICandidato, Candidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from '../candidato.service';
import { CandidatoAllComponent } from './candidato-all.component';

@Injectable({ providedIn: 'root' })
export class CandidatoResolve implements Resolve<ICandidato> {
  constructor(private service: CandidatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICandidato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((candidato: HttpResponse<Candidato>) => {
          if (candidato.body) {
            return of(candidato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Candidato());
  }
}

export const candidatoRoute: Route = {
  path: 'candidato/all',
  component: CandidatoAllComponent,
  data: {
    authorities: [Authority.USER],
    defaultSort: 'id,asc',
    pageTitle: 'rdcJhipsterApp.candidato.home.title',
  },
  canActivate: [UserRouteAccessService],
};
