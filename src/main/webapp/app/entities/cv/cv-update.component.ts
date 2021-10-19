import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICv, Cv } from 'app/shared/model/cv.model';
import { CvService } from './cv.service';
import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from 'app/entities/candidato/candidato.service';

@Component({
  selector: 'jhi-cv-update',
  templateUrl: './cv-update.component.html',
})
export class CvUpdateComponent implements OnInit {
  isSaving = false;
  candidatoes: ICandidato[] = [];
  inserimentoDp: any;

  editForm = this.fb.group({
    id: [],
    inserimento: [],
    candidato: [],
  });

  constructor(
    protected cvService: CvService,
    protected candidatoService: CandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cv }) => {
      this.updateForm(cv);

      this.candidatoService.query().subscribe((res: HttpResponse<ICandidato[]>) => (this.candidatoes = res.body || []));
    });
  }

  updateForm(cv: ICv): void {
    this.editForm.patchValue({
      id: cv.id,
      inserimento: cv.inserimento,
      candidato: cv.candidato,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cv = this.createFromForm();
    if (cv.id !== undefined) {
      this.subscribeToSaveResponse(this.cvService.update(cv));
    } else {
      this.subscribeToSaveResponse(this.cvService.create(cv));
    }
  }

  private createFromForm(): ICv {
    return {
      ...new Cv(),
      id: this.editForm.get(['id'])!.value,
      inserimento: this.editForm.get(['inserimento'])!.value,
      candidato: this.editForm.get(['candidato'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICv>>): void {
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

  trackById(index: number, item: ICandidato): any {
    return item.id;
  }
}
