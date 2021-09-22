import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { EsperienzaUpdateComponent } from 'app/entities/esperienza/esperienza-update.component';
import { EsperienzaService } from 'app/entities/esperienza/esperienza.service';
import { Esperienza } from 'app/shared/model/esperienza.model';

describe('Component Tests', () => {
  describe('Esperienza Management Update Component', () => {
    let comp: EsperienzaUpdateComponent;
    let fixture: ComponentFixture<EsperienzaUpdateComponent>;
    let service: EsperienzaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [EsperienzaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EsperienzaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EsperienzaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EsperienzaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Esperienza(123);
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
        const entity = new Esperienza();
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
