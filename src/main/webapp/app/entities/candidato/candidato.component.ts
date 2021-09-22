import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';
import { CandidatoDeleteDialogComponent } from './candidato-delete-dialog.component';

@Component({
  selector: 'jhi-candidato',
  templateUrl: './candidato.component.html',
})
export class CandidatoComponent implements OnInit, OnDestroy {
  candidatoes?: ICandidato[];
  eventSubscriber?: Subscription;

  constructor(protected candidatoService: CandidatoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.candidatoService.query().subscribe((res: HttpResponse<ICandidato[]>) => (this.candidatoes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCandidatoes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICandidato): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCandidatoes(): void {
    this.eventSubscriber = this.eventManager.subscribe('candidatoListModification', () => this.loadAll());
  }

  delete(candidato: ICandidato): void {
    const modalRef = this.modalService.open(CandidatoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.candidato = candidato;
  }
}
