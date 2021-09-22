import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICv } from 'app/shared/model/cv.model';
import { CvService } from './cv.service';
import { CvDeleteDialogComponent } from './cv-delete-dialog.component';

@Component({
  selector: 'jhi-cv',
  templateUrl: './cv.component.html',
})
export class CvComponent implements OnInit, OnDestroy {
  cvs?: ICv[];
  eventSubscriber?: Subscription;

  constructor(protected cvService: CvService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.cvService.query().subscribe((res: HttpResponse<ICv[]>) => (this.cvs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCvs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICv): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCvs(): void {
    this.eventSubscriber = this.eventManager.subscribe('cvListModification', () => this.loadAll());
  }

  delete(cv: ICv): void {
    const modalRef = this.modalService.open(CvDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cv = cv;
  }
}
