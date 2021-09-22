import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';

@Component({
  selector: 'jhi-cv-istruzione-detail',
  templateUrl: './cv-istruzione-detail.component.html',
})
export class CvIstruzioneDetailComponent implements OnInit {
  cvIstruzione: ICvIstruzione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cvIstruzione }) => (this.cvIstruzione = cvIstruzione));
  }

  previousState(): void {
    window.history.back();
  }
}
