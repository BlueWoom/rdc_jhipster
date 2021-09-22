import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { OccupazioneUpdateComponent } from 'app/entities/occupazione/occupazione-update.component';
import { OccupazioneService } from 'app/entities/occupazione/occupazione.service';
import { Occupazione } from 'app/shared/model/occupazione.model';

describe('Component Tests', () => {
  describe('Occupazione Management Update Component', () => {
    let comp: OccupazioneUpdateComponent;
    let fixture: ComponentFixture<OccupazioneUpdateComponent>;
    let service: OccupazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OccupazioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OccupazioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OccupazioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OccupazioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Occupazione(123);
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
        const entity = new Occupazione();
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
