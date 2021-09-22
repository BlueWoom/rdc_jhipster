import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGapAnalysis } from 'app/shared/model/gap-analysis.model';
import { GapAnalysisService } from './gap-analysis.service';

@Component({
  templateUrl: './gap-analysis-delete-dialog.component.html',
})
export class GapAnalysisDeleteDialogComponent {
  gapAnalysis?: IGapAnalysis;

  constructor(
    protected gapAnalysisService: GapAnalysisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gapAnalysisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gapAnalysisListModification');
      this.activeModal.close();
    });
  }
}
