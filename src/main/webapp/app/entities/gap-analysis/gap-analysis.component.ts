import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGapAnalysis } from 'app/shared/model/gap-analysis.model';
import { GapAnalysisService } from './gap-analysis.service';
import { GapAnalysisDeleteDialogComponent } from './gap-analysis-delete-dialog.component';

@Component({
  selector: 'jhi-gap-analysis',
  templateUrl: './gap-analysis.component.html',
})
export class GapAnalysisComponent implements OnInit, OnDestroy {
  gapAnalyses?: IGapAnalysis[];
  eventSubscriber?: Subscription;

  constructor(
    protected gapAnalysisService: GapAnalysisService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.gapAnalysisService.query().subscribe((res: HttpResponse<IGapAnalysis[]>) => (this.gapAnalyses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGapAnalyses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGapAnalysis): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGapAnalyses(): void {
    this.eventSubscriber = this.eventManager.subscribe('gapAnalysisListModification', () => this.loadAll());
  }

  delete(gapAnalysis: IGapAnalysis): void {
    const modalRef = this.modalService.open(GapAnalysisDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gapAnalysis = gapAnalysis;
  }
}
