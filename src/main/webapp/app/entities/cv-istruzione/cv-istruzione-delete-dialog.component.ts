import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';
import { CvIstruzioneService } from './cv-istruzione.service';

@Component({
  templateUrl: './cv-istruzione-delete-dialog.component.html',
})
export class CvIstruzioneDeleteDialogComponent {
  cvIstruzione?: ICvIstruzione;

  constructor(
    protected cvIstruzioneService: CvIstruzioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cvIstruzioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cvIstruzioneListModification');
      this.activeModal.close();
    });
  }
}
