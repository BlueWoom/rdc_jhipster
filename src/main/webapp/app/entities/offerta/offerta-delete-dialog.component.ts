import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferta } from 'app/shared/model/offerta.model';
import { OffertaService } from './offerta.service';

@Component({
  templateUrl: './offerta-delete-dialog.component.html',
})
export class OffertaDeleteDialogComponent {
  offerta?: IOfferta;

  constructor(protected offertaService: OffertaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.offertaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offertaListModification');
      this.activeModal.close();
    });
  }
}
