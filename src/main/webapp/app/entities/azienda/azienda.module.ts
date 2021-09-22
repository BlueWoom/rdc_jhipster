import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { AziendaComponent } from './azienda.component';
import { AziendaDetailComponent } from './azienda-detail.component';
import { AziendaUpdateComponent } from './azienda-update.component';
import { AziendaDeleteDialogComponent } from './azienda-delete-dialog.component';
import { aziendaRoute } from './azienda.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(aziendaRoute)],
  declarations: [AziendaComponent, AziendaDetailComponent, AziendaUpdateComponent, AziendaDeleteDialogComponent],
  entryComponents: [AziendaDeleteDialogComponent],
})
export class RdcAziendaModule {}
