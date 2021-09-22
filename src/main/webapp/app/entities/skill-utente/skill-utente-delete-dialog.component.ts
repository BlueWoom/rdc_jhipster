import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISkillUtente } from 'app/shared/model/skill-utente.model';
import { SkillUtenteService } from './skill-utente.service';

@Component({
  templateUrl: './skill-utente-delete-dialog.component.html',
})
export class SkillUtenteDeleteDialogComponent {
  skillUtente?: ISkillUtente;

  constructor(
    protected skillUtenteService: SkillUtenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.skillUtenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('skillUtenteListModification');
      this.activeModal.close();
    });
  }
}
