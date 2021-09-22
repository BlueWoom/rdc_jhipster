import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICv } from 'app/shared/model/cv.model';
import { CvService } from './cv.service';

@Component({
  templateUrl: './cv-delete-dialog.component.html',
})
export class CvDeleteDialogComponent {
  cv?: ICv;

  constructor(protected cvService: CvService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cvService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cvListModification');
      this.activeModal.close();
    });
  }
}
