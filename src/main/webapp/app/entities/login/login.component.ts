import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILogin } from 'app/shared/model/login.model';
import { LoginService } from './login.service';
import { LoginDeleteDialogComponent } from './login-delete-dialog.component';

@Component({
  selector: 'jhi-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit, OnDestroy {
  logins?: ILogin[];
  eventSubscriber?: Subscription;

  constructor(protected loginService: LoginService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.loginService.query().subscribe((res: HttpResponse<ILogin[]>) => (this.logins = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLogins();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILogin): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLogins(): void {
    this.eventSubscriber = this.eventManager.subscribe('loginListModification', () => this.loadAll());
  }

  delete(login: ILogin): void {
    const modalRef = this.modalService.open(LoginDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.login = login;
  }
}
