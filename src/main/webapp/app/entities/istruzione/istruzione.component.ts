import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IIstruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from './istruzione.service';
import { IstruzioneDeleteDialogComponent } from './istruzione-delete-dialog.component';

@Component({
  selector: 'jhi-istruzione',
  templateUrl: './istruzione.component.html',
})
export class IstruzioneComponent implements OnInit, OnDestroy {
  istruziones?: IIstruzione[];
  eventSubscriber?: Subscription;

  constructor(protected istruzioneService: IstruzioneService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.istruzioneService.query().subscribe((res: HttpResponse<IIstruzione[]>) => (this.istruziones = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInIstruziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IIstruzione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInIstruziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('istruzioneListModification', () => this.loadAll());
  }

  delete(istruzione: IIstruzione): void {
    const modalRef = this.modalService.open(IstruzioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.istruzione = istruzione;
  }
}
