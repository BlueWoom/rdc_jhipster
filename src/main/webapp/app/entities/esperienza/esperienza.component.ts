import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEsperienza } from 'app/shared/model/esperienza.model';
import { EsperienzaService } from './esperienza.service';
import { EsperienzaDeleteDialogComponent } from './esperienza-delete-dialog.component';

@Component({
  selector: 'jhi-esperienza',
  templateUrl: './esperienza.component.html',
})
export class EsperienzaComponent implements OnInit, OnDestroy {
  esperienzas?: IEsperienza[];
  eventSubscriber?: Subscription;

  constructor(protected esperienzaService: EsperienzaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.esperienzaService.query().subscribe((res: HttpResponse<IEsperienza[]>) => (this.esperienzas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEsperienzas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEsperienza): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEsperienzas(): void {
    this.eventSubscriber = this.eventManager.subscribe('esperienzaListModification', () => this.loadAll());
  }

  delete(esperienza: IEsperienza): void {
    const modalRef = this.modalService.open(EsperienzaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.esperienza = esperienza;
  }
}
