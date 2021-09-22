import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OffertaOccupazioneRichiestaUpdateComponent } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta-update.component';
import { OffertaOccupazioneRichiestaService } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta.service';
import { OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

describe('Component Tests', () => {
  describe('OffertaOccupazioneRichiesta Management Update Component', () => {
    let comp: OffertaOccupazioneRichiestaUpdateComponent;
    let fixture: ComponentFixture<OffertaOccupazioneRichiestaUpdateComponent>;
    let service: OffertaOccupazioneRichiestaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OffertaOccupazioneRichiestaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OffertaOccupazioneRichiestaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaOccupazioneRichiestaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaOccupazioneRichiestaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OffertaOccupazioneRichiesta(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OffertaOccupazioneRichiesta();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
