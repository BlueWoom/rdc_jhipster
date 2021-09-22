import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISkillUtente } from 'app/shared/model/skill-utente.model';
import { SkillUtenteService } from './skill-utente.service';
import { SkillUtenteDeleteDialogComponent } from './skill-utente-delete-dialog.component';

@Component({
  selector: 'jhi-skill-utente',
  templateUrl: './skill-utente.component.html',
})
export class SkillUtenteComponent implements OnInit, OnDestroy {
  skillUtentes?: ISkillUtente[];
  eventSubscriber?: Subscription;

  constructor(
    protected skillUtenteService: SkillUtenteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.skillUtenteService.query().subscribe((res: HttpResponse<ISkillUtente[]>) => (this.skillUtentes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSkillUtentes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISkillUtente): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSkillUtentes(): void {
    this.eventSubscriber = this.eventManager.subscribe('skillUtenteListModification', () => this.loadAll());
  }

  delete(skillUtente: ISkillUtente): void {
    const modalRef = this.modalService.open(SkillUtenteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.skillUtente = skillUtente;
  }
}
