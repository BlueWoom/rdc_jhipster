import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { GapAnalysisComponent } from './gap-analysis.component';
import { GapAnalysisDetailComponent } from './gap-analysis-detail.component';
import { GapAnalysisUpdateComponent } from './gap-analysis-update.component';
import { GapAnalysisDeleteDialogComponent } from './gap-analysis-delete-dialog.component';
import { gapAnalysisRoute } from './gap-analysis.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(gapAnalysisRoute)],
  declarations: [GapAnalysisComponent, GapAnalysisDetailComponent, GapAnalysisUpdateComponent, GapAnalysisDeleteDialogComponent],
  entryComponents: [GapAnalysisDeleteDialogComponent],
})
export class RdcGapAnalysisModule {}
