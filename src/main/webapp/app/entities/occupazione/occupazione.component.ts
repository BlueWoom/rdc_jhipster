import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOccupazione } from 'app/shared/model/occupazione.model';
import { OccupazioneService } from './occupazione.service';
import { OccupazioneDeleteDialogComponent } from './occupazione-delete-dialog.component';

@Component({
  selector: 'jhi-occupazione',
  templateUrl: './occupazione.component.html',
})
export class OccupazioneComponent implements OnInit, OnDestroy {
  occupaziones?: IOccupazione[];
  eventSubscriber?: Subscription;

  constructor(
    protected occupazioneService: OccupazioneService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.occupazioneService.query().subscribe((res: HttpResponse<IOccupazione[]>) => (this.occupaziones = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOccupaziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOccupazione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOccupaziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('occupazioneListModification', () => this.loadAll());
  }

  delete(occupazione: IOccupazione): void {
    const modalRef = this.modalService.open(OccupazioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.occupazione = occupazione;
  }
}
