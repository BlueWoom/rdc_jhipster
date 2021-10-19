import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

import { Authority } from './../../shared/constants/authority.constants';
import { RegistersComponent } from './registers.component';

export const homeRegistersRoute: Route = {
  path: 'home',
  component: RegistersComponent,
  data: {
    authorities: [Authority.ADMIN],
    pageTitle: 'registers.title',
  },
  canActivate: [UserRouteAccessService],
};
