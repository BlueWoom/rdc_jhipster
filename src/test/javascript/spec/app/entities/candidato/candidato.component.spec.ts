import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { CandidatoComponent } from 'app/entities/candidato/candidato.component';
import { CandidatoService } from 'app/entities/candidato/candidato.service';
import { Candidato } from 'app/shared/model/candidato.model';

describe('Component Tests', () => {
  describe('Candidato Management Component', () => {
    let comp: CandidatoComponent;
    let fixture: ComponentFixture<CandidatoComponent>;
    let service: CandidatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [CandidatoComponent],
      })
        .overrideTemplate(CandidatoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CandidatoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CandidatoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Candidato(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.candidatoes && comp.candidatoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
