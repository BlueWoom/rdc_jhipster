import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';

import { RegistersComponent } from './home/registers.component';

import { registersRoute } from './registers.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(registersRoute)],
  declarations: [RegistersComponent],
  entryComponents: [],
})
export class RegistersModule {}
