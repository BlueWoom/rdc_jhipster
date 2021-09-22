import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { EsperienzaComponent } from './esperienza.component';
import { EsperienzaDetailComponent } from './esperienza-detail.component';
import { EsperienzaUpdateComponent } from './esperienza-update.component';
import { EsperienzaDeleteDialogComponent } from './esperienza-delete-dialog.component';
import { esperienzaRoute } from './esperienza.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(esperienzaRoute)],
  declarations: [EsperienzaComponent, EsperienzaDetailComponent, EsperienzaUpdateComponent, EsperienzaDeleteDialogComponent],
  entryComponents: [EsperienzaDeleteDialogComponent],
})
export class RdcEsperienzaModule {}
