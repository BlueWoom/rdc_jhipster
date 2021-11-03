import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators } from '@angular/forms';

import { IAzienda, Azienda } from './../../shared/model/azienda.model';
import { AziendaService } from 'app/entities/azienda/azienda.service';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterService } from '../register.service';

import { Provincia } from 'app/shared/enum/provincia.ts';
import { Regione } from 'app/shared/enum/regione.ts';

@Component({
  selector: 'jhi-azienda-create-update',
  templateUrl: './azienda-create-update.component.html',
})
export class AziendaCreateUpdateComponent implements OnInit, OnDestroy {
  isSaving = false;
  isModified = false;

  tabGeneral = true;
  tabUser = false;
  tabDashboarding = false;

  editForm = this.fb.group({
    id: [],
    cf: [null, [Validators.required]],
    ragioneSociale: [null, [Validators.required]],
    indirizzoSede: [null, [Validators.required]],
    citta: [null, [Validators.required]],
    provinciaSede: [null, [Validators.required]],
    regioneSede: [null, [Validators.required]],
    capSede: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
  });

  azienda: IAzienda = new Azienda();

  province = Object.values(Provincia);
  regioni = Object.values(Regione);

  constructor(
    private fb: FormBuilder,
    protected aziendaService: AziendaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected registerService: RegisterService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ azienda }) => {
      this.updateForm(azienda);
    });
  }

  ngOnDestroy(): void {}

  updateForm(azienda: IAzienda): void {
    this.editForm.patchValue({
      id: azienda.id,
      cf: azienda.cf,
      ragioneSociale: azienda.ragioneSociale,
      indirizzoSede: azienda.indirizzoSede,
      provinciaSede: azienda.provinciaSede,
      regioneSede: azienda.regioneSede,
      capSede: azienda.capSede,
    });
  }

  private createFromForm(): IAzienda {
    return {
      ...new Azienda(),
      id: this.editForm.get(['id'])!.value,
      cf: this.editForm.get(['cf'])!.value,
      ragioneSociale: this.editForm.get(['ragioneSociale'])!.value,
      indirizzoSede: this.editForm.get(['indirizzoSede'])!.value,
      provinciaSede: this.editForm.get(['provinciaSede'])!.value,
      regioneSede: this.editForm.get(['regioneSede'])!.value,
      capSede: this.editForm.get(['capSede'])!.value,
    };
  }

  previousState(): void {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAzienda>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.isModified = false;
    this.editForm.markAsPristine();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  save(): void {
    this.isSaving = true;
    const azienda = this.createFromForm();
    if (azienda.id !== undefined && azienda.id !== null) {
      this.subscribeToSaveResponse(this.registerService.updateAziendaGeneralInfo(azienda));
    } else {
      this.subscribeToSaveResponse(this.aziendaService.create(azienda));
    }
  }
}
