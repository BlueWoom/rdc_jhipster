import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIstruzione } from 'app/shared/model/istruzione.model';

@Component({
  selector: 'jhi-istruzione-detail',
  templateUrl: './istruzione-detail.component.html',
})
export class IstruzioneDetailComponent implements OnInit {
  istruzione: IIstruzione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ istruzione }) => (this.istruzione = istruzione));
  }

  previousState(): void {
    window.history.back();
  }
}
