import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOccupazione } from 'app/shared/model/occupazione.model';

@Component({
  selector: 'jhi-occupazione-detail',
  templateUrl: './occupazione-detail.component.html',
})
export class OccupazioneDetailComponent implements OnInit {
  occupazione: IOccupazione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ occupazione }) => (this.occupazione = occupazione));
  }

  previousState(): void {
    window.history.back();
  }
}
