import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INavigator } from 'app/shared/model/navigator.model';
import { NavigatorService } from './navigator.service';

@Component({
  templateUrl: './navigator-delete-dialog.component.html',
})
export class NavigatorDeleteDialogComponent {
  navigator?: INavigator;

  constructor(protected navigatorService: NavigatorService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.navigatorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('navigatorListModification');
      this.activeModal.close();
    });
  }
}
