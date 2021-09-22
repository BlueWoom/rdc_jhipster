import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';

@Component({
  selector: 'jhi-offerta-skill-detail',
  templateUrl: './offerta-skill-detail.component.html',
})
export class OffertaSkillDetailComponent implements OnInit {
  offertaSkill: IOffertaSkill | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offertaSkill }) => (this.offertaSkill = offertaSkill));
  }

  previousState(): void {
    window.history.back();
  }
}
