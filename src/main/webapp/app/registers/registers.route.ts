import { Routes } from '@angular/router';

import { homeRegistersRoute } from './home/registers.route';
import { aziendaRoute } from './azienda-all/azienda-all.route';
import { candidatoRoute } from './candidato-all/candidato-all.route';
import { offertaRoute } from './offerta-all/offerta-all.route';

const REGISTRIES_ROUTES = [homeRegistersRoute, aziendaRoute, candidatoRoute, offertaRoute];

export const registersRoute: Routes = [
  {
    path: '',
    children: REGISTRIES_ROUTES,
  },
];
