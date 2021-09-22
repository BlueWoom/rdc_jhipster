import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { OffertaOccupazioneRichiestaService } from './offerta-occupazione-richiesta.service';

@Component({
  templateUrl: './offerta-occupazione-richiesta-delete-dialog.component.html',
})
export class OffertaOccupazioneRichiestaDeleteDialogComponent {
  offertaOccupazioneRichiesta?: IOffertaOccupazioneRichiesta;

  constructor(
    protected offertaOccupazioneRichiestaService: OffertaOccupazioneRichiestaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.offertaOccupazioneRichiestaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offertaOccupazioneRichiestaListModification');
      this.activeModal.close();
    });
  }
}
