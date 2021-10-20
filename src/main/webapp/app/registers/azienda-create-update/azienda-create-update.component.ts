import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators } from '@angular/forms';

import { IAzienda, Azienda } from './../../shared/model/azienda.model';
import { AziendaService } from '../azienda.service';

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

  constructor(
    private fb: FormBuilder,
    protected aziendaService: AziendaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  ngOnInit(): void {}

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
      ragioneSociale: this.editForm.get(['regioneSociale'])!.value,
      indirizzoSede: this.editForm.get(['indirizzoSede'])!.value,
      provinciaSede: this.editForm.get(['provinciaSede'])!.value,
      regioneSede: this.editForm.get(['regioneSede'])!.value,
      capSede: this.editForm.get(['capSede'])!.value,
    };
  }

  previousState(): void {
    window.history.back();
  }
}
