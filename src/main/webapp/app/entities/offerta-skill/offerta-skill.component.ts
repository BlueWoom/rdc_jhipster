import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';
import { OffertaSkillService } from './offerta-skill.service';
import { OffertaSkillDeleteDialogComponent } from './offerta-skill-delete-dialog.component';

@Component({
  selector: 'jhi-offerta-skill',
  templateUrl: './offerta-skill.component.html',
})
export class OffertaSkillComponent implements OnInit, OnDestroy {
  offertaSkills?: IOffertaSkill[];
  eventSubscriber?: Subscription;

  constructor(
    protected offertaSkillService: OffertaSkillService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.offertaSkillService.query().subscribe((res: HttpResponse<IOffertaSkill[]>) => (this.offertaSkills = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOffertaSkills();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOffertaSkill): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOffertaSkills(): void {
    this.eventSubscriber = this.eventManager.subscribe('offertaSkillListModification', () => this.loadAll());
  }

  delete(offertaSkill: IOffertaSkill): void {
    const modalRef = this.modalService.open(OffertaSkillDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offertaSkill = offertaSkill;
  }
}
