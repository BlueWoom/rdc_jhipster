import { Route } from '@angular/router';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { AziendaAllComponent } from './azienda-all.component';

export const aziendaRoute: Route = {
  path: 'azienda/all',
  component: AziendaAllComponent,
  data: {
    authorities: [Authority.USER],
    defaultSort: 'id,asc',
    pageTitle: 'rdcJhipsterApp.azienda.home.title',
  },
  canActivate: [UserRouteAccessService],
};
