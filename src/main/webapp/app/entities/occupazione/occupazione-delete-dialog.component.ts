import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOccupazione } from 'app/shared/model/occupazione.model';
import { OccupazioneService } from './occupazione.service';

@Component({
  templateUrl: './occupazione-delete-dialog.component.html',
})
export class OccupazioneDeleteDialogComponent {
  occupazione?: IOccupazione;

  constructor(
    protected occupazioneService: OccupazioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.occupazioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('occupazioneListModification');
      this.activeModal.close();
    });
  }
}
