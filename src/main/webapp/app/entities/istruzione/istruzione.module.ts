import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { IstruzioneComponent } from './istruzione.component';
import { IstruzioneDetailComponent } from './istruzione-detail.component';
import { IstruzioneUpdateComponent } from './istruzione-update.component';
import { IstruzioneDeleteDialogComponent } from './istruzione-delete-dialog.component';
import { istruzioneRoute } from './istruzione.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(istruzioneRoute)],
  declarations: [IstruzioneComponent, IstruzioneDetailComponent, IstruzioneUpdateComponent, IstruzioneDeleteDialogComponent],
  entryComponents: [IstruzioneDeleteDialogComponent],
})
export class RdcJhipsterIstruzioneModule {}
