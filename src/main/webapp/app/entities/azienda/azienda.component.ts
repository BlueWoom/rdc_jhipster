import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAzienda } from 'app/shared/model/azienda.model';
import { AziendaService } from './azienda.service';
import { AziendaDeleteDialogComponent } from './azienda-delete-dialog.component';

@Component({
  selector: 'jhi-azienda',
  templateUrl: './azienda.component.html',
})
export class AziendaComponent implements OnInit, OnDestroy {
  aziendas?: IAzienda[];
  eventSubscriber?: Subscription;

  constructor(protected aziendaService: AziendaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.aziendaService.query().subscribe((res: HttpResponse<IAzienda[]>) => (this.aziendas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAziendas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAzienda): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAziendas(): void {
    this.eventSubscriber = this.eventManager.subscribe('aziendaListModification', () => this.loadAll());
  }

  delete(azienda: IAzienda): void {
    const modalRef = this.modalService.open(AziendaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.azienda = azienda;
  }
}
