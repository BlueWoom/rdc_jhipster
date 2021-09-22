import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEsperienza, Esperienza } from 'app/shared/model/esperienza.model';
import { EsperienzaService } from './esperienza.service';
import { ICv } from 'app/shared/model/cv.model';
import { CvService } from 'app/entities/cv/cv.service';

@Component({
  selector: 'jhi-esperienza-update',
  templateUrl: './esperienza-update.component.html',
})
export class EsperienzaUpdateComponent implements OnInit {
  isSaving = false;
  cvs: ICv[] = [];
  dalDp: any;
  alDp: any;

  editForm = this.fb.group({
    id: [],
    codice: [null, [Validators.pattern('[0-9]+')]],
    attivita: [],
    dal: [],
    al: [],
    datoreLavoro: [],
    sedeLavoro: [],
    cv: [],
  });

  constructor(
    protected esperienzaService: EsperienzaService,
    protected cvService: CvService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ esperienza }) => {
      this.updateForm(esperienza);

      this.cvService.query().subscribe((res: HttpResponse<ICv[]>) => (this.cvs = res.body || []));
    });
  }

  updateForm(esperienza: IEsperienza): void {
    this.editForm.patchValue({
      id: esperienza.id,
      codice: esperienza.codice,
      attivita: esperienza.attivita,
      dal: esperienza.dal,
      al: esperienza.al,
      datoreLavoro: esperienza.datoreLavoro,
      sedeLavoro: esperienza.sedeLavoro,
      cv: esperienza.cv,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const esperienza = this.createFromForm();
    if (esperienza.id !== undefined) {
      this.subscribeToSaveResponse(this.esperienzaService.update(esperienza));
    } else {
      this.subscribeToSaveResponse(this.esperienzaService.create(esperienza));
    }
  }

  private createFromForm(): IEsperienza {
    return {
      ...new Esperienza(),
      id: this.editForm.get(['id'])!.value,
      codice: this.editForm.get(['codice'])!.value,
      attivita: this.editForm.get(['attivita'])!.value,
      dal: this.editForm.get(['dal'])!.value,
      al: this.editForm.get(['al'])!.value,
      datoreLavoro: this.editForm.get(['datoreLavoro'])!.value,
      sedeLavoro: this.editForm.get(['sedeLavoro'])!.value,
      cv: this.editForm.get(['cv'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEsperienza>>): void {
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

  trackById(index: number, item: ICv): any {
    return item.id;
  }
}
