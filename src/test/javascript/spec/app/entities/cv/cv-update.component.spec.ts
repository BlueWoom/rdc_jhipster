import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { CvUpdateComponent } from 'app/entities/cv/cv-update.component';
import { CvService } from 'app/entities/cv/cv.service';
import { Cv } from 'app/shared/model/cv.model';

describe('Component Tests', () => {
  describe('Cv Management Update Component', () => {
    let comp: CvUpdateComponent;
    let fixture: ComponentFixture<CvUpdateComponent>;
    let service: CvService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [CvUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CvUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CvUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CvService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cv(123);
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
        const entity = new Cv();
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
