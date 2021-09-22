import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OffertaSkillDetailComponent } from 'app/entities/offerta-skill/offerta-skill-detail.component';
import { OffertaSkill } from 'app/shared/model/offerta-skill.model';

describe('Component Tests', () => {
  describe('OffertaSkill Management Detail Component', () => {
    let comp: OffertaSkillDetailComponent;
    let fixture: ComponentFixture<OffertaSkillDetailComponent>;
    const route = ({ data: of({ offertaSkill: new OffertaSkill(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OffertaSkillDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OffertaSkillDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OffertaSkillDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offertaSkill on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offertaSkill).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
