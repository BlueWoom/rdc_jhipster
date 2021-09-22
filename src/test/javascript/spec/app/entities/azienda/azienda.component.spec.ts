import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { AziendaComponent } from 'app/entities/azienda/azienda.component';
import { AziendaService } from 'app/entities/azienda/azienda.service';
import { Azienda } from 'app/shared/model/azienda.model';

describe('Component Tests', () => {
  describe('Azienda Management Component', () => {
    let comp: AziendaComponent;
    let fixture: ComponentFixture<AziendaComponent>;
    let service: AziendaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [AziendaComponent],
      })
        .overrideTemplate(AziendaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AziendaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AziendaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Azienda(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aziendas && comp.aziendas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
