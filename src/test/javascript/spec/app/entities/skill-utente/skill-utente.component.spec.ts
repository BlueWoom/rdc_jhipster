import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { SkillUtenteComponent } from 'app/entities/skill-utente/skill-utente.component';
import { SkillUtenteService } from 'app/entities/skill-utente/skill-utente.service';
import { SkillUtente } from 'app/shared/model/skill-utente.model';

describe('Component Tests', () => {
  describe('SkillUtente Management Component', () => {
    let comp: SkillUtenteComponent;
    let fixture: ComponentFixture<SkillUtenteComponent>;
    let service: SkillUtenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [SkillUtenteComponent],
      })
        .overrideTemplate(SkillUtenteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillUtenteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillUtenteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SkillUtente(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.skillUtentes && comp.skillUtentes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
