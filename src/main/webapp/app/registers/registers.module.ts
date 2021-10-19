import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from './../shared/shared.module';

import { RegistersComponent } from './home/registers.component';

import { registersRoute } from './registers.route';

import { AziendaAllComponent } from './azienda-all/azienda-all.component';

import { RdcJhipsterAziendaModule } from './../entities/azienda/azienda.module';

@NgModule({
  imports: [RdcJhipsterSharedModule, RdcJhipsterAziendaModule, RouterModule.forChild(registersRoute)],
  declarations: [RegistersComponent, AziendaAllComponent],
  entryComponents: [],
})
export class RegistersModule {}
