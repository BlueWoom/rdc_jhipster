import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOffertaSkill, OffertaSkill } from 'app/shared/model/offerta-skill.model';
import { OffertaSkillService } from './offerta-skill.service';
import { ISkill } from 'app/shared/model/skill.model';
import { SkillService } from 'app/entities/skill/skill.service';
import { IOfferta } from 'app/shared/model/offerta.model';
import { OffertaService } from 'app/entities/offerta/offerta.service';

type SelectableEntity = ISkill | IOfferta;

@Component({
  selector: 'jhi-offerta-skill-update',
  templateUrl: './offerta-skill-update.component.html',
})
export class OffertaSkillUpdateComponent implements OnInit {
  isSaving = false;
  skills: ISkill[] = [];
  offertas: IOfferta[] = [];

  editForm = this.fb.group({
    id: [],
    optional: [],
    skill: [],
    offerta: [],
  });

  constructor(
    protected offertaSkillService: OffertaSkillService,
    protected skillService: SkillService,
    protected offertaService: OffertaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offertaSkill }) => {
      this.updateForm(offertaSkill);

      this.skillService.query().subscribe((res: HttpResponse<ISkill[]>) => (this.skills = res.body || []));

      this.offertaService.query().subscribe((res: HttpResponse<IOfferta[]>) => (this.offertas = res.body || []));
    });
  }

  updateForm(offertaSkill: IOffertaSkill): void {
    this.editForm.patchValue({
      id: offertaSkill.id,
      optional: offertaSkill.optional,
      skill: offertaSkill.skill,
      offerta: offertaSkill.offerta,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offertaSkill = this.createFromForm();
    if (offertaSkill.id !== undefined) {
      this.subscribeToSaveResponse(this.offertaSkillService.update(offertaSkill));
    } else {
      this.subscribeToSaveResponse(this.offertaSkillService.create(offertaSkill));
    }
  }

  private createFromForm(): IOffertaSkill {
    return {
      ...new OffertaSkill(),
      id: this.editForm.get(['id'])!.value,
      optional: this.editForm.get(['optional'])!.value,
      skill: this.editForm.get(['skill'])!.value,
      offerta: this.editForm.get(['offerta'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffertaSkill>>): void {
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
