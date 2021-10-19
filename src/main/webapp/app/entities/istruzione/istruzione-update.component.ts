import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IIstruzione, Istruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from './istruzione.service';

@Component({
  selector: 'jhi-istruzione-update',
  templateUrl: './istruzione-update.component.html',
})
export class IstruzioneUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codiceIsced: [],
    codiceLivello: [],
    nome: [],
    campoStudio: [],
    sinonimi: [],
    codiceIstituto: [],
    tipoIstituto: [],
  });

  constructor(protected istruzioneService: IstruzioneService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ istruzione }) => {
      this.updateForm(istruzione);
    });
  }

  updateForm(istruzione: IIstruzione): void {
    this.editForm.patchValue({
      id: istruzione.id,
      codiceIsced: istruzione.codiceIsced,
      codiceLivello: istruzione.codiceLivello,
      nome: istruzione.nome,
      campoStudio: istruzione.campoStudio,
      sinonimi: istruzione.sinonimi,
      codiceIstituto: istruzione.codiceIstituto,
      tipoIstituto: istruzione.tipoIstituto,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const istruzione = this.createFromForm();
    if (istruzione.id !== undefined) {
      this.subscribeToSaveResponse(this.istruzioneService.update(istruzione));
    } else {
      this.subscribeToSaveResponse(this.istruzioneService.create(istruzione));
    }
  }

  private createFromForm(): IIstruzione {
    return {
      ...new Istruzione(),
      id: this.editForm.get(['id'])!.value,
      codiceIsced: this.editForm.get(['codiceIsced'])!.value,
      codiceLivello: this.editForm.get(['codiceLivello'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      campoStudio: this.editForm.get(['campoStudio'])!.value,
      sinonimi: this.editForm.get(['sinonimi'])!.value,
      codiceIstituto: this.editForm.get(['codiceIstituto'])!.value,
      tipoIstituto: this.editForm.get(['tipoIstituto'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIstruzione>>): void {
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
