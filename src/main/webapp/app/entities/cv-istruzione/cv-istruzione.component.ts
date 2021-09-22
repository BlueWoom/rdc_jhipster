import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';
import { CvIstruzioneService } from './cv-istruzione.service';
import { CvIstruzioneDeleteDialogComponent } from './cv-istruzione-delete-dialog.component';

@Component({
  selector: 'jhi-cv-istruzione',
  templateUrl: './cv-istruzione.component.html',
})
export class CvIstruzioneComponent implements OnInit, OnDestroy {
  cvIstruziones?: ICvIstruzione[];
  eventSubscriber?: Subscription;

  constructor(
    protected cvIstruzioneService: CvIstruzioneService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.cvIstruzioneService.query().subscribe((res: HttpResponse<ICvIstruzione[]>) => (this.cvIstruziones = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCvIstruziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICvIstruzione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCvIstruziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('cvIstruzioneListModification', () => this.loadAll());
  }

  delete(cvIstruzione: ICvIstruzione): void {
    const modalRef = this.modalService.open(CvIstruzioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cvIstruzione = cvIstruzione;
  }
}
