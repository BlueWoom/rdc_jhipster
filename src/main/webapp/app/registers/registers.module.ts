import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from './../shared/shared.module';

import { RegistersComponent } from './home/registers.component';

import { registersRoute } from './registers.route';

import { AziendaAllComponent } from './azienda-all/azienda-all.component';
import { RdcJhipsterAziendaModule } from './../entities/azienda/azienda.module';
import { RdcJhipsterCandidatoModule } from './../entities/candidato/candidato.module';
import { CandidatoAllComponent } from './candidato-all/candidato-all.component';
import { OffertaAllComponent } from './offerta-all/offerta-all.component';
import { RdcJhipsterOffertaModule } from './../entities/offerta/offerta.module';

@NgModule({
  imports: [
    RdcJhipsterSharedModule,
    RdcJhipsterAziendaModule,
    RdcJhipsterCandidatoModule,
    RdcJhipsterOffertaModule,
    RouterModule.forChild(registersRoute),
  ],
  declarations: [RegistersComponent, AziendaAllComponent, CandidatoAllComponent, OffertaAllComponent],
  entryComponents: [],
})
export class RegistersModule {}
