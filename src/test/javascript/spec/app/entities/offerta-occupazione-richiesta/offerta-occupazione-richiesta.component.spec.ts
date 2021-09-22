import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OffertaOccupazioneRichiestaComponent } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta.component';
import { OffertaOccupazioneRichiestaService } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta.service';
import { OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

describe('Component Tests', () => {
  describe('OffertaOccupazioneRichiesta Management Component', () => {
    let comp: OffertaOccupazioneRichiestaComponent;
    let fixture: ComponentFixture<OffertaOccupazioneRichiestaComponent>;
    let service: OffertaOccupazioneRichiestaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OffertaOccupazioneRichiestaComponent],
      })
        .overrideTemplate(OffertaOccupazioneRichiestaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaOccupazioneRichiestaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaOccupazioneRichiestaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OffertaOccupazioneRichiesta(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offertaOccupazioneRichiestas && comp.offertaOccupazioneRichiestas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
