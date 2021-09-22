import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { CvComponent } from './cv.component';
import { CvDetailComponent } from './cv-detail.component';
import { CvUpdateComponent } from './cv-update.component';
import { CvDeleteDialogComponent } from './cv-delete-dialog.component';
import { cvRoute } from './cv.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(cvRoute)],
  declarations: [CvComponent, CvDetailComponent, CvUpdateComponent, CvDeleteDialogComponent],
  entryComponents: [CvDeleteDialogComponent],
})
export class RdcCvModule {}
