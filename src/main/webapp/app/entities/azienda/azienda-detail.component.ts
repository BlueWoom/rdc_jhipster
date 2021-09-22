import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAzienda } from 'app/shared/model/azienda.model';

@Component({
  selector: 'jhi-azienda-detail',
  templateUrl: './azienda-detail.component.html',
})
export class AziendaDetailComponent implements OnInit {
  azienda: IAzienda | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ azienda }) => (this.azienda = azienda));
  }

  previousState(): void {
    window.history.back();
  }
}
