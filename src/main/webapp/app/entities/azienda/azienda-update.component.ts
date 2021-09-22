import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAzienda, Azienda } from 'app/shared/model/azienda.model';
import { AziendaService } from './azienda.service';

@Component({
  selector: 'jhi-azienda-update',
  templateUrl: './azienda-update.component.html',
})
export class AziendaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cf: [],
    ragioneSociale: [],
    indirizzoSede: [],
    provinciaSede: [],
    ragioneSede: [],
    cittaSede: [],
    capSede: [null, [Validators.pattern('[0-9]+')]],
  });

  constructor(protected aziendaService: AziendaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ azienda }) => {
      this.updateForm(azienda);
    });
  }

  updateForm(azienda: IAzienda): void {
    this.editForm.patchValue({
      id: azienda.id,
      cf: azienda.cf,
      ragioneSociale: azienda.ragioneSociale,
      indirizzoSede: azienda.indirizzoSede,
      provinciaSede: azienda.provinciaSede,
      ragioneSede: azienda.ragioneSede,
      cittaSede: azienda.cittaSede,
      capSede: azienda.capSede,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const azienda = this.createFromForm();
    if (azienda.id !== undefined) {
      this.subscribeToSaveResponse(this.aziendaService.update(azienda));
    } else {
      this.subscribeToSaveResponse(this.aziendaService.create(azienda));
    }
  }

  private createFromForm(): IAzienda {
    return {
      ...new Azienda(),
      id: this.editForm.get(['id'])!.value,
      cf: this.editForm.get(['cf'])!.value,
      ragioneSociale: this.editForm.get(['ragioneSociale'])!.value,
      indirizzoSede: this.editForm.get(['indirizzoSede'])!.value,
      provinciaSede: this.editForm.get(['provinciaSede'])!.value,
      ragioneSede: this.editForm.get(['ragioneSede'])!.value,
      cittaSede: this.editForm.get(['cittaSede'])!.value,
      capSede: this.editForm.get(['capSede'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAzienda>>): void {
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
