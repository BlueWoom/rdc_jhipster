import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { CvIstruzioneComponent } from './cv-istruzione.component';
import { CvIstruzioneDetailComponent } from './cv-istruzione-detail.component';
import { CvIstruzioneUpdateComponent } from './cv-istruzione-update.component';
import { CvIstruzioneDeleteDialogComponent } from './cv-istruzione-delete-dialog.component';
import { cvIstruzioneRoute } from './cv-istruzione.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(cvIstruzioneRoute)],
  declarations: [CvIstruzioneComponent, CvIstruzioneDetailComponent, CvIstruzioneUpdateComponent, CvIstruzioneDeleteDialogComponent],
  entryComponents: [CvIstruzioneDeleteDialogComponent],
})
export class RdcJhipsterCvIstruzioneModule {}
