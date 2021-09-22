import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { OffertaComponent } from './offerta.component';
import { OffertaDetailComponent } from './offerta-detail.component';
import { OffertaUpdateComponent } from './offerta-update.component';
import { OffertaDeleteDialogComponent } from './offerta-delete-dialog.component';
import { offertaRoute } from './offerta.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(offertaRoute)],
  declarations: [OffertaComponent, OffertaDetailComponent, OffertaUpdateComponent, OffertaDeleteDialogComponent],
  entryComponents: [OffertaDeleteDialogComponent],
})
export class RdcJhipsterOffertaModule {}
