import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOfferta, Offerta } from 'app/shared/model/offerta.model';
import { OffertaService } from './offerta.service';
import { IIstruzione } from 'app/shared/model/istruzione.model';
import { IstruzioneService } from 'app/entities/istruzione/istruzione.service';
import { IAzienda } from 'app/shared/model/azienda.model';
import { AziendaService } from 'app/entities/azienda/azienda.service';

type SelectableEntity = IIstruzione | IAzienda;

@Component({
  selector: 'jhi-offerta-update',
  templateUrl: './offerta-update.component.html',
})
export class OffertaUpdateComponent implements OnInit {
  isSaving = false;
  istruziones: IIstruzione[] = [];
  aziendas: IAzienda[] = [];
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    codice: [null, [Validators.pattern('[0-9]+')]],
    data: [],
    indirizzoSede: [],
    cittaSede: [],
    capSede: [null, [Validators.pattern('[0-9]+')]],
    provinciaSede: [],
    istruzione: [],
    azienda: [],
  });

  constructor(
    protected offertaService: OffertaService,
    protected istruzioneService: IstruzioneService,
    protected aziendaService: AziendaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerta }) => {
      this.updateForm(offerta);

      this.istruzioneService.query().subscribe((res: HttpResponse<IIstruzione[]>) => (this.istruziones = res.body || []));

      this.aziendaService.query().subscribe((res: HttpResponse<IAzienda[]>) => (this.aziendas = res.body || []));
    });
  }

  updateForm(offerta: IOfferta): void {
    this.editForm.patchValue({
      id: offerta.id,
      codice: offerta.codice,
      data: offerta.data,
      indirizzoSede: offerta.indirizzoSede,
      cittaSede: offerta.cittaSede,
      capSede: offerta.capSede,
      provinciaSede: offerta.provinciaSede,
      istruzione: offerta.istruzione,
      azienda: offerta.azienda,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerta = this.createFromForm();
    if (offerta.id !== undefined) {
      this.subscribeToSaveResponse(this.offertaService.update(offerta));
    } else {
      this.subscribeToSaveResponse(this.offertaService.create(offerta));
    }
  }

  private createFromForm(): IOfferta {
    return {
      ...new Offerta(),
      id: this.editForm.get(['id'])!.value,
      codice: this.editForm.get(['codice'])!.value,
      data: this.editForm.get(['data'])!.value,
      indirizzoSede: this.editForm.get(['indirizzoSede'])!.value,
      cittaSede: this.editForm.get(['cittaSede'])!.value,
      capSede: this.editForm.get(['capSede'])!.value,
      provinciaSede: this.editForm.get(['provinciaSede'])!.value,
      istruzione: this.editForm.get(['istruzione'])!.value,
      azienda: this.editForm.get(['azienda'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferta>>): void {
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
