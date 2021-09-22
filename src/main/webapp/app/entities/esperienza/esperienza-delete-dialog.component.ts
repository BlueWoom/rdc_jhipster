import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEsperienza } from 'app/shared/model/esperienza.model';
import { EsperienzaService } from './esperienza.service';

@Component({
  templateUrl: './esperienza-delete-dialog.component.html',
})
export class EsperienzaDeleteDialogComponent {
  esperienza?: IEsperienza;

  constructor(
    protected esperienzaService: EsperienzaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.esperienzaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('esperienzaListModification');
      this.activeModal.close();
    });
  }
}
