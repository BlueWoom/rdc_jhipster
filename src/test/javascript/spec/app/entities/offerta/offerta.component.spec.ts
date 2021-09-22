import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { OffertaComponent } from 'app/entities/offerta/offerta.component';
import { OffertaService } from 'app/entities/offerta/offerta.service';
import { Offerta } from 'app/shared/model/offerta.model';

describe('Component Tests', () => {
  describe('Offerta Management Component', () => {
    let comp: OffertaComponent;
    let fixture: ComponentFixture<OffertaComponent>;
    let service: OffertaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OffertaComponent],
      })
        .overrideTemplate(OffertaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Offerta(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offertas && comp.offertas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
