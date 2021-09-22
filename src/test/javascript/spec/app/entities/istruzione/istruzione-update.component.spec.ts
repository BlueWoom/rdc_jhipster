import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { IstruzioneUpdateComponent } from 'app/entities/istruzione/istruzione-update.component';
import { IstruzioneService } from 'app/entities/istruzione/istruzione.service';
import { Istruzione } from 'app/shared/model/istruzione.model';

describe('Component Tests', () => {
  describe('Istruzione Management Update Component', () => {
    let comp: IstruzioneUpdateComponent;
    let fixture: ComponentFixture<IstruzioneUpdateComponent>;
    let service: IstruzioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [IstruzioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(IstruzioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IstruzioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IstruzioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Istruzione(123);
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
        const entity = new Istruzione();
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
