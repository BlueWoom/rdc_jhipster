import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { EsperienzaComponent } from './esperienza.component';
import { EsperienzaDetailComponent } from './esperienza-detail.component';
import { EsperienzaUpdateComponent } from './esperienza-update.component';
import { EsperienzaDeleteDialogComponent } from './esperienza-delete-dialog.component';
import { esperienzaRoute } from './esperienza.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(esperienzaRoute)],
  declarations: [EsperienzaComponent, EsperienzaDetailComponent, EsperienzaUpdateComponent, EsperienzaDeleteDialogComponent],
  entryComponents: [EsperienzaDeleteDialogComponent],
})
export class RdcJhipsterEsperienzaModule {}
