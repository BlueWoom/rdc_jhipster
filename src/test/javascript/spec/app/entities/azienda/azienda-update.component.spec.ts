import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { AziendaUpdateComponent } from 'app/entities/azienda/azienda-update.component';
import { AziendaService } from 'app/entities/azienda/azienda.service';
import { Azienda } from 'app/shared/model/azienda.model';

describe('Component Tests', () => {
  describe('Azienda Management Update Component', () => {
    let comp: AziendaUpdateComponent;
    let fixture: ComponentFixture<AziendaUpdateComponent>;
    let service: AziendaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [AziendaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AziendaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AziendaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AziendaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Azienda(123);
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
        const entity = new Azienda();
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
