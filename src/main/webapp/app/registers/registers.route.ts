import { Routes } from '@angular/router';

import { homeRegistersRoute } from './home/registers.route';
import { aziendaRoute } from './azienda-all/azienda-all.route';
import { aziendaCreateUpdateRoute } from './azienda-create-update/azienda-create-update.route';
import { candidatoRoute } from './candidato-all/candidato-all.route';
import { offertaRoute } from './offerta-all/offerta-all.route';

const REGISTRIES_ROUTES = [homeRegistersRoute, aziendaRoute, ...aziendaCreateUpdateRoute, candidatoRoute, offertaRoute];

export const registersRoute: Routes = [
  {
    path: '',
    children: REGISTRIES_ROUTES,
  },
];
