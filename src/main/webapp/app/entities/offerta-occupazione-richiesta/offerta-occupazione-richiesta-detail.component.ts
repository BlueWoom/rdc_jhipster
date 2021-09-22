import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

@Component({
  selector: 'jhi-offerta-occupazione-richiesta-detail',
  templateUrl: './offerta-occupazione-richiesta-detail.component.html',
})
export class OffertaOccupazioneRichiestaDetailComponent implements OnInit {
  offertaOccupazioneRichiesta: IOffertaOccupazioneRichiesta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ offertaOccupazioneRichiesta }) => (this.offertaOccupazioneRichiesta = offertaOccupazioneRichiesta)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
