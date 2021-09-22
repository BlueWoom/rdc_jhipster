import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { CvIstruzioneUpdateComponent } from 'app/entities/cv-istruzione/cv-istruzione-update.component';
import { CvIstruzioneService } from 'app/entities/cv-istruzione/cv-istruzione.service';
import { CvIstruzione } from 'app/shared/model/cv-istruzione.model';

describe('Component Tests', () => {
  describe('CvIstruzione Management Update Component', () => {
    let comp: CvIstruzioneUpdateComponent;
    let fixture: ComponentFixture<CvIstruzioneUpdateComponent>;
    let service: CvIstruzioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [CvIstruzioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CvIstruzioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CvIstruzioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CvIstruzioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CvIstruzione(123);
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
        const entity = new CvIstruzione();
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
