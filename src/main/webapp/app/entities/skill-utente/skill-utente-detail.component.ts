import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISkillUtente } from 'app/shared/model/skill-utente.model';

@Component({
  selector: 'jhi-skill-utente-detail',
  templateUrl: './skill-utente-detail.component.html',
})
export class SkillUtenteDetailComponent implements OnInit {
  skillUtente: ISkillUtente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillUtente }) => (this.skillUtente = skillUtente));
  }

  previousState(): void {
    window.history.back();
  }
}
