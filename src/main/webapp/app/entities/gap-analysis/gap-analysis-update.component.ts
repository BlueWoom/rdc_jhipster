import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGapAnalysis, GapAnalysis } from 'app/shared/model/gap-analysis.model';
import { GapAnalysisService } from './gap-analysis.service';

@Component({
  selector: 'jhi-gap-analysis-update',
  templateUrl: './gap-analysis-update.component.html',
})
export class GapAnalysisUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codiceEsco: [],
    nome: [],
    frequenza: [],
  });

  constructor(protected gapAnalysisService: GapAnalysisService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gapAnalysis }) => {
      this.updateForm(gapAnalysis);
    });
  }

  updateForm(gapAnalysis: IGapAnalysis): void {
    this.editForm.patchValue({
      id: gapAnalysis.id,
      codiceEsco: gapAnalysis.codiceEsco,
      nome: gapAnalysis.nome,
      frequenza: gapAnalysis.frequenza,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gapAnalysis = this.createFromForm();
    if (gapAnalysis.id !== undefined) {
      this.subscribeToSaveResponse(this.gapAnalysisService.update(gapAnalysis));
    } else {
      this.subscribeToSaveResponse(this.gapAnalysisService.create(gapAnalysis));
    }
  }

  private createFromForm(): IGapAnalysis {
    return {
      ...new GapAnalysis(),
      id: this.editForm.get(['id'])!.value,
      codiceEsco: this.editForm.get(['codiceEsco'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      frequenza: this.editForm.get(['frequenza'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGapAnalysis>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
