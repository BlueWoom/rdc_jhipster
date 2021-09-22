import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGapAnalysis, GapAnalysis } from 'app/shared/model/gap-analysis.model';
import { GapAnalysisService } from './gap-analysis.service';
import { GapAnalysisComponent } from './gap-analysis.component';
import { GapAnalysisDetailComponent } from './gap-analysis-detail.component';
import { GapAnalysisUpdateComponent } from './gap-analysis-update.component';

@Injectable({ providedIn: 'root' })
export class GapAnalysisResolve implements Resolve<IGapAnalysis> {
  constructor(private service: GapAnalysisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGapAnalysis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gapAnalysis: HttpResponse<GapAnalysis>) => {
          if (gapAnalysis.body) {
            return of(gapAnalysis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GapAnalysis());
  }
}

export const gapAnalysisRoute: Routes = [
  {
    path: '',
    component: GapAnalysisComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.gapAnalysis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GapAnalysisDetailComponent,
    resolve: {
      gapAnalysis: GapAnalysisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.gapAnalysis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GapAnalysisUpdateComponent,
    resolve: {
      gapAnalysis: GapAnalysisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.gapAnalysis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GapAnalysisUpdateComponent,
    resolve: {
      gapAnalysis: GapAnalysisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'rdcApp.gapAnalysis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
