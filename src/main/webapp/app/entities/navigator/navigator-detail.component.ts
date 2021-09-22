import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INavigator } from 'app/shared/model/navigator.model';

@Component({
  selector: 'jhi-navigator-detail',
  templateUrl: './navigator-detail.component.html',
})
export class NavigatorDetailComponent implements OnInit {
  navigator: INavigator | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ navigator }) => (this.navigator = navigator));
  }

  previousState(): void {
    window.history.back();
  }
}
