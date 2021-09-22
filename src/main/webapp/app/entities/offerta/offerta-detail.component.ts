import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferta } from 'app/shared/model/offerta.model';

@Component({
  selector: 'jhi-offerta-detail',
  templateUrl: './offerta-detail.component.html',
})
export class OffertaDetailComponent implements OnInit {
  offerta: IOfferta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerta }) => (this.offerta = offerta));
  }

  previousState(): void {
    window.history.back();
  }
}
