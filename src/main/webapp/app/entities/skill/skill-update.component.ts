import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISkill, Skill } from 'app/shared/model/skill.model';
import { SkillService } from './skill.service';

@Component({
  selector: 'jhi-skill-update',
  templateUrl: './skill-update.component.html',
})
export class SkillUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codiceEsco: [null, [Validators.pattern('[0-9]+')]],
    nome: [],
    tipo: [],
  });

  constructor(protected skillService: SkillService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skill }) => {
      this.updateForm(skill);
    });
  }

  updateForm(skill: ISkill): void {
    this.editForm.patchValue({
      id: skill.id,
      codiceEsco: skill.codiceEsco,
      nome: skill.nome,
      tipo: skill.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const skill = this.createFromForm();
    if (skill.id !== undefined) {
      this.subscribeToSaveResponse(this.skillService.update(skill));
    } else {
      this.subscribeToSaveResponse(this.skillService.create(skill));
    }
  }

  private createFromForm(): ISkill {
    return {
      ...new Skill(),
      id: this.editForm.get(['id'])!.value,
      codiceEsco: this.editForm.get(['codiceEsco'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISkill>>): void {
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
