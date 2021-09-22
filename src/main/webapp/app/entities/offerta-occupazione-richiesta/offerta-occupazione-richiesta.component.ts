import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { OffertaOccupazioneRichiestaService } from './offerta-occupazione-richiesta.service';
import { OffertaOccupazioneRichiestaDeleteDialogComponent } from './offerta-occupazione-richiesta-delete-dialog.component';

@Component({
  selector: 'jhi-offerta-occupazione-richiesta',
  templateUrl: './offerta-occupazione-richiesta.component.html',
})
export class OffertaOccupazioneRichiestaComponent implements OnInit, OnDestroy {
  offertaOccupazioneRichiestas?: IOffertaOccupazioneRichiesta[];
  eventSubscriber?: Subscription;

  constructor(
    protected offertaOccupazioneRichiestaService: OffertaOccupazioneRichiestaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.offertaOccupazioneRichiestaService
      .query()
      .subscribe((res: HttpResponse<IOffertaOccupazioneRichiesta[]>) => (this.offertaOccupazioneRichiestas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOffertaOccupazioneRichiestas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOffertaOccupazioneRichiesta): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOffertaOccupazioneRichiestas(): void {
    this.eventSubscriber = this.eventManager.subscribe('offertaOccupazioneRichiestaListModification', () => this.loadAll());
  }

  delete(offertaOccupazioneRichiesta: IOffertaOccupazioneRichiesta): void {
    const modalRef = this.modalService.open(OffertaOccupazioneRichiestaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offertaOccupazioneRichiesta = offertaOccupazioneRichiesta;
  }
}
