import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INavigator } from 'app/shared/model/navigator.model';
import { NavigatorService } from './navigator.service';
import { NavigatorDeleteDialogComponent } from './navigator-delete-dialog.component';

@Component({
  selector: 'jhi-navigator',
  templateUrl: './navigator.component.html',
})
export class NavigatorComponent implements OnInit, OnDestroy {
  navigators?: INavigator[];
  eventSubscriber?: Subscription;

  constructor(protected navigatorService: NavigatorService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.navigatorService.query().subscribe((res: HttpResponse<INavigator[]>) => (this.navigators = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNavigators();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INavigator): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNavigators(): void {
    this.eventSubscriber = this.eventManager.subscribe('navigatorListModification', () => this.loadAll());
  }

  delete(navigator: INavigator): void {
    const modalRef = this.modalService.open(NavigatorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.navigator = navigator;
  }
}
