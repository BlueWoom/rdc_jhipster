import { Routes } from '@angular/router';

import { homeRegistersRoute } from './home/registers.route';

const REGISTRIES_ROUTES = [homeRegistersRoute];

export const registersRoute: Routes = [
  {
    path: '',
    children: REGISTRIES_ROUTES,
  },
];
