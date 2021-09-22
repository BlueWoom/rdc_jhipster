import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';
import { OffertaSkillService } from './offerta-skill.service';

@Component({
  templateUrl: './offerta-skill-delete-dialog.component.html',
})
export class OffertaSkillDeleteDialogComponent {
  offertaSkill?: IOffertaSkill;

  constructor(
    protected offertaSkillService: OffertaSkillService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.offertaSkillService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offertaSkillListModification');
      this.activeModal.close();
    });
  }
}
