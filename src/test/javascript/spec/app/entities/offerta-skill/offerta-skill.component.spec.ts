import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { OffertaSkillComponent } from 'app/entities/offerta-skill/offerta-skill.component';
import { OffertaSkillService } from 'app/entities/offerta-skill/offerta-skill.service';
import { OffertaSkill } from 'app/shared/model/offerta-skill.model';

describe('Component Tests', () => {
  describe('OffertaSkill Management Component', () => {
    let comp: OffertaSkillComponent;
    let fixture: ComponentFixture<OffertaSkillComponent>;
    let service: OffertaSkillService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OffertaSkillComponent],
      })
        .overrideTemplate(OffertaSkillComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaSkillComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaSkillService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OffertaSkill(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offertaSkills && comp.offertaSkills[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
