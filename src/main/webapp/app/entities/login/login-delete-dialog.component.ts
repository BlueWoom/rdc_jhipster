import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogin } from 'app/shared/model/login.model';
import { LoginService } from './login.service';

@Component({
  templateUrl: './login-delete-dialog.component.html',
})
export class LoginDeleteDialogComponent {
  login?: ILogin;

  constructor(protected loginService: LoginService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.loginService.delete(id).subscribe(() => {
      this.eventManager.broadcast('loginListModification');
      this.activeModal.close();
    });
  }
}
