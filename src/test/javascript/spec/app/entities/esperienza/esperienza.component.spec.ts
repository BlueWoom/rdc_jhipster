import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { EsperienzaComponent } from 'app/entities/esperienza/esperienza.component';
import { EsperienzaService } from 'app/entities/esperienza/esperienza.service';
import { Esperienza } from 'app/shared/model/esperienza.model';

describe('Component Tests', () => {
  describe('Esperienza Management Component', () => {
    let comp: EsperienzaComponent;
    let fixture: ComponentFixture<EsperienzaComponent>;
    let service: EsperienzaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [EsperienzaComponent],
      })
        .overrideTemplate(EsperienzaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EsperienzaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EsperienzaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Esperienza(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.esperienzas && comp.esperienzas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
