import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { OccupazioneComponent } from './occupazione.component';
import { OccupazioneDetailComponent } from './occupazione-detail.component';
import { OccupazioneUpdateComponent } from './occupazione-update.component';
import { OccupazioneDeleteDialogComponent } from './occupazione-delete-dialog.component';
import { occupazioneRoute } from './occupazione.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(occupazioneRoute)],
  declarations: [OccupazioneComponent, OccupazioneDetailComponent, OccupazioneUpdateComponent, OccupazioneDeleteDialogComponent],
  entryComponents: [OccupazioneDeleteDialogComponent],
})
export class RdcJhipsterOccupazioneModule {}
