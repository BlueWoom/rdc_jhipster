import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIstruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from './istruzione.service';

@Component({
  templateUrl: './istruzione-delete-dialog.component.html',
})
export class IstruzioneDeleteDialogComponent {
  istruzione?: IIstruzione;

  constructor(
    protected istruzioneService: IstruzioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.istruzioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('istruzioneListModification');
      this.activeModal.close();
    });
  }
}
