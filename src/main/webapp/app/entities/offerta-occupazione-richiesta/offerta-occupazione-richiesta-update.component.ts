import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOffertaOccupazioneRichiesta, OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { OffertaOccupazioneRichiestaService } from './offerta-occupazione-richiesta.service';
import { IOfferta } from 'app/shared/model/offerta.model';
import { OffertaService } from 'app/entities/offerta/offerta.service';
import { IOccupazione } from 'app/shared/model/occupazione.model';
import { OccupazioneService } from 'app/entities/occupazione/occupazione.service';

type SelectableEntity = IOfferta | IOccupazione;

@Component({
  selector: 'jhi-offerta-occupazione-richiesta-update',
  templateUrl: './offerta-occupazione-richiesta-update.component.html',
})
export class OffertaOccupazioneRichiestaUpdateComponent implements OnInit {
  isSaving = false;
  offertas: IOfferta[] = [];
  occupaziones: IOccupazione[] = [];

  editForm = this.fb.group({
    id: [],
    codiceOfferta: [null, [Validators.pattern('[0-9]+')]],
    codiceEscoOccupazione: [],
    anni: [],
    offerta: [],
    occupazione: [],
  });

  constructor(
    protected offertaOccupazioneRichiestaService: OffertaOccupazioneRichiestaService,
    protected offertaService: OffertaService,
    protected occupazioneService: OccupazioneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offertaOccupazioneRichiesta }) => {
      this.updateForm(offertaOccupazioneRichiesta);

      this.offertaService.query().subscribe((res: HttpResponse<IOfferta[]>) => (this.offertas = res.body || []));

      this.occupazioneService.query().subscribe((res: HttpResponse<IOccupazione[]>) => (this.occupaziones = res.body || []));
    });
  }

  updateForm(offertaOccupazioneRichiesta: IOffertaOccupazioneRichiesta): void {
    this.editForm.patchValue({
      id: offertaOccupazioneRichiesta.id,
      codiceOfferta: offertaOccupazioneRichiesta.codiceOfferta,
      codiceEscoOccupazione: offertaOccupazioneRichiesta.codiceEscoOccupazione,
      anni: offertaOccupazioneRichiesta.anni,
      offerta: offertaOccupazioneRichiesta.offerta,
      occupazione: offertaOccupazioneRichiesta.occupazione,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offertaOccupazioneRichiesta = this.createFromForm();
    if (offertaOccupazioneRichiesta.id !== undefined) {
      this.subscribeToSaveResponse(this.offertaOccupazioneRichiestaService.update(offertaOccupazioneRichiesta));
    } else {
      this.subscribeToSaveResponse(this.offertaOccupazioneRichiestaService.create(offertaOccupazioneRichiesta));
    }
  }

  private createFromForm(): IOffertaOccupazioneRichiesta {
    return {
      ...new OffertaOccupazioneRichiesta(),
      id: this.editForm.get(['id'])!.value,
      codiceOfferta: this.editForm.get(['codiceOfferta'])!.value,
      codiceEscoOccupazione: this.editForm.get(['codiceEscoOccupazione'])!.value,
      anni: this.editForm.get(['anni'])!.value,
      offerta: this.editForm.get(['offerta'])!.value,
      occupazione: this.editForm.get(['occupazione'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffertaOccupazioneRichiesta>>): void {
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
