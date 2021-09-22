import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEsperienza } from 'app/shared/model/esperienza.model';

@Component({
  selector: 'jhi-esperienza-detail',
  templateUrl: './esperienza-detail.component.html',
})
export class EsperienzaDetailComponent implements OnInit {
  esperienza: IEsperienza | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ esperienza }) => (this.esperienza = esperienza));
  }

  previousState(): void {
    window.history.back();
  }
}
