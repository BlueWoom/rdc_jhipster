import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICvIstruzione, CvIstruzione } from 'app/shared/model/cv-istruzione.model';
import { CvIstruzioneService } from './cv-istruzione.service';
import { IIstruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from 'app/entities/istruzione/istruzione.service';
import { ICv } from 'app/shared/model/cv.model';
import { CvService } from 'app/entities/cv/cv.service';

type SelectableEntity = IIstruzione | ICv;

@Component({
  selector: 'jhi-cv-istruzione-update',
  templateUrl: './cv-istruzione-update.component.html',
})
export class CvIstruzioneUpdateComponent implements OnInit {
  isSaving = false;
  istruziones: IIstruzione[] = [];
  cvs: ICv[] = [];

  editForm = this.fb.group({
    id: [],
    punteggio: [],
    istruzione: [],
    cv: [],
  });

  constructor(
    protected cvIstruzioneService: CvIstruzioneService,
    protected istruzioneService: IstruzioneService,
    protected cvService: CvService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cvIstruzione }) => {
      this.updateForm(cvIstruzione);

      this.istruzioneService.query().subscribe((res: HttpResponse<IIstruzione[]>) => (this.istruziones = res.body || []));

      this.cvService.query().subscribe((res: HttpResponse<ICv[]>) => (this.cvs = res.body || []));
    });
  }

  updateForm(cvIstruzione: ICvIstruzione): void {
    this.editForm.patchValue({
      id: cvIstruzione.id,
      punteggio: cvIstruzione.punteggio,
      istruzione: cvIstruzione.istruzione,
      cv: cvIstruzione.cv,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cvIstruzione = this.createFromForm();
    if (cvIstruzione.id !== undefined) {
      this.subscribeToSaveResponse(this.cvIstruzioneService.update(cvIstruzione));
    } else {
      this.subscribeToSaveResponse(this.cvIstruzioneService.create(cvIstruzione));
    }
  }

  private createFromForm(): ICvIstruzione {
    return {
      ...new CvIstruzione(),
      id: this.editForm.get(['id'])!.value,
      punteggio: this.editForm.get(['punteggio'])!.value,
      istruzione: this.editForm.get(['istruzione'])!.value,
      cv: this.editForm.get(['cv'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICvIstruzione>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
