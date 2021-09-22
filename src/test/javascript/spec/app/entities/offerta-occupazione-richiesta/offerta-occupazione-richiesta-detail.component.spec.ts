import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { OffertaOccupazioneRichiestaDetailComponent } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta-detail.component';
import { OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

describe('Component Tests', () => {
  describe('OffertaOccupazioneRichiesta Management Detail Component', () => {
    let comp: OffertaOccupazioneRichiestaDetailComponent;
    let fixture: ComponentFixture<OffertaOccupazioneRichiestaDetailComponent>;
    const route = ({ data: of({ offertaOccupazioneRichiesta: new OffertaOccupazioneRichiesta(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OffertaOccupazioneRichiestaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OffertaOccupazioneRichiestaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OffertaOccupazioneRichiestaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offertaOccupazioneRichiesta on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offertaOccupazioneRichiesta).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
