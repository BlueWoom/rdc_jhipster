import { Routes } from '@angular/router';

import { homeRegistersRoute } from './home/registers.route';
import { aziendaRoute } from './azienda-all/azienda-all.route';

const REGISTRIES_ROUTES = [homeRegistersRoute, aziendaRoute];

export const registersRoute: Routes = [
  {
    path: '',
    children: REGISTRIES_ROUTES,
  },
];
