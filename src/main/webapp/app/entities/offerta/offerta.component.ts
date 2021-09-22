import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfferta } from 'app/shared/model/offerta.model';
import { OffertaService } from './offerta.service';
import { OffertaDeleteDialogComponent } from './offerta-delete-dialog.component';

@Component({
  selector: 'jhi-offerta',
  templateUrl: './offerta.component.html',
})
export class OffertaComponent implements OnInit, OnDestroy {
  offertas?: IOfferta[];
  eventSubscriber?: Subscription;

  constructor(protected offertaService: OffertaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.offertaService.query().subscribe((res: HttpResponse<IOfferta[]>) => (this.offertas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOffertas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOfferta): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOffertas(): void {
    this.eventSubscriber = this.eventManager.subscribe('offertaListModification', () => this.loadAll());
  }

  delete(offerta: IOfferta): void {
    const modalRef = this.modalService.open(OffertaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offerta = offerta;
  }
}
