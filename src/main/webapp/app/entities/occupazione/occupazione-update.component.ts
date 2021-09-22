import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOccupazione, Occupazione } from 'app/shared/model/occupazione.model';
import { OccupazioneService } from './occupazione.service';
import { IEsperienza } from 'app/shared/model/esperienza.model';
import { EsperienzaService } from 'app/entities/esperienza/esperienza.service';
import { IOfferta } from 'app/shared/model/offerta.model';
import { OffertaService } from 'app/entities/offerta/offerta.service';
import { ISkill } from 'app/shared/model/skill.model';
import { SkillService } from 'app/entities/skill/skill.service';

type SelectableEntity = IEsperienza | IOfferta | ISkill;

@Component({
  selector: 'jhi-occupazione-update',
  templateUrl: './occupazione-update.component.html',
})
export class OccupazioneUpdateComponent implements OnInit {
  isSaving = false;
  esperienzas: IEsperienza[] = [];
  offertas: IOfferta[] = [];
  skills: ISkill[] = [];

  editForm = this.fb.group({
    id: [],
    codiceEsco: [],
    nome: [],
    esperienzas: [],
    offertas: [],
    skills: [],
  });

  constructor(
    protected occupazioneService: OccupazioneService,
    protected esperienzaService: EsperienzaService,
    protected offertaService: OffertaService,
    protected skillService: SkillService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ occupazione }) => {
      this.updateForm(occupazione);

      this.esperienzaService.query().subscribe((res: HttpResponse<IEsperienza[]>) => (this.esperienzas = res.body || []));

      this.offertaService.query().subscribe((res: HttpResponse<IOfferta[]>) => (this.offertas = res.body || []));

      this.skillService.query().subscribe((res: HttpResponse<ISkill[]>) => (this.skills = res.body || []));
    });
  }

  updateForm(occupazione: IOccupazione): void {
    this.editForm.patchValue({
      id: occupazione.id,
      codiceEsco: occupazione.codiceEsco,
      nome: occupazione.nome,
      esperienzas: occupazione.esperienzas,
      offertas: occupazione.offertas,
      skills: occupazione.skills,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const occupazione = this.createFromForm();
    if (occupazione.id !== undefined) {
      this.subscribeToSaveResponse(this.occupazioneService.update(occupazione));
    } else {
      this.subscribeToSaveResponse(this.occupazioneService.create(occupazione));
    }
  }

  private createFromForm(): IOccupazione {
    return {
      ...new Occupazione(),
      id: this.editForm.get(['id'])!.value,
      codiceEsco: this.editForm.get(['codiceEsco'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      esperienzas: this.editForm.get(['esperienzas'])!.value,
      offertas: this.editForm.get(['offertas'])!.value,
      skills: this.editForm.get(['skills'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOccupazione>>): void {
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

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
