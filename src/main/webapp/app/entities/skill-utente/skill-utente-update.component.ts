import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISkillUtente, SkillUtente } from 'app/shared/model/skill-utente.model';
import { SkillUtenteService } from './skill-utente.service';
import { ISkill } from 'app/shared/model/skill.model';
import { SkillService } from 'app/entities/skill/skill.service';
import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from 'app/entities/candidato/candidato.service';

type SelectableEntity = ISkill | ICandidato;

@Component({
  selector: 'jhi-skill-utente-update',
  templateUrl: './skill-utente-update.component.html',
})
export class SkillUtenteUpdateComponent implements OnInit {
  isSaving = false;
  skills: ISkill[] = [];
  candidatoes: ICandidato[] = [];

  editForm = this.fb.group({
    id: [],
    cfUtente: [],
    codiceEscoSkill: [],
    skill: [],
    candidato: [],
  });

  constructor(
    protected skillUtenteService: SkillUtenteService,
    protected skillService: SkillService,
    protected candidatoService: CandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillUtente }) => {
      this.updateForm(skillUtente);

      this.skillService.query().subscribe((res: HttpResponse<ISkill[]>) => (this.skills = res.body || []));

      this.candidatoService.query().subscribe((res: HttpResponse<ICandidato[]>) => (this.candidatoes = res.body || []));
    });
  }

  updateForm(skillUtente: ISkillUtente): void {
    this.editForm.patchValue({
      id: skillUtente.id,
      cfUtente: skillUtente.cfUtente,
      codiceEscoSkill: skillUtente.codiceEscoSkill,
      skill: skillUtente.skill,
      candidato: skillUtente.candidato,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const skillUtente = this.createFromForm();
    if (skillUtente.id !== undefined) {
      this.subscribeToSaveResponse(this.skillUtenteService.update(skillUtente));
    } else {
      this.subscribeToSaveResponse(this.skillUtenteService.create(skillUtente));
    }
  }

  private createFromForm(): ISkillUtente {
    return {
      ...new SkillUtente(),
      id: this.editForm.get(['id'])!.value,
      cfUtente: this.editForm.get(['cfUtente'])!.value,
      codiceEscoSkill: this.editForm.get(['codiceEscoSkill'])!.value,
      skill: this.editForm.get(['skill'])!.value,
      candidato: this.editForm.get(['candidato'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISkillUtente>>): void {
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
