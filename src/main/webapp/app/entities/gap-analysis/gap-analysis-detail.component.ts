import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGapAnalysis } from 'app/shared/model/gap-analysis.model';

@Component({
  selector: 'jhi-gap-analysis-detail',
  templateUrl: './gap-analysis-detail.component.html',
})
export class GapAnalysisDetailComponent implements OnInit {
  gapAnalysis: IGapAnalysis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gapAnalysis }) => (this.gapAnalysis = gapAnalysis));
  }

  previousState(): void {
    window.history.back();
  }
}
