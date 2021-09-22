import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OffertaUpdateComponent } from 'app/entities/offerta/offerta-update.component';
import { OffertaService } from 'app/entities/offerta/offerta.service';
import { Offerta } from 'app/shared/model/offerta.model';

describe('Component Tests', () => {
  describe('Offerta Management Update Component', () => {
    let comp: OffertaUpdateComponent;
    let fixture: ComponentFixture<OffertaUpdateComponent>;
    let service: OffertaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OffertaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OffertaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Offerta(123);
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
        const entity = new Offerta();
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
