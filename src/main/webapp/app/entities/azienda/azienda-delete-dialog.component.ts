import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAzienda } from 'app/shared/model/azienda.model';
import { AziendaService } from './azienda.service';

@Component({
  templateUrl: './azienda-delete-dialog.component.html',
})
export class AziendaDeleteDialogComponent {
  azienda?: IAzienda;

  constructor(protected aziendaService: AziendaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.aziendaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('aziendaListModification');
      this.activeModal.close();
    });
  }
}
