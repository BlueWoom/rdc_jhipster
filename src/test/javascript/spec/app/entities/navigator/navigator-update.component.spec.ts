import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { NavigatorUpdateComponent } from 'app/entities/navigator/navigator-update.component';
import { NavigatorService } from 'app/entities/navigator/navigator.service';
import { Navigator } from 'app/shared/model/navigator.model';

describe('Component Tests', () => {
  describe('Navigator Management Update Component', () => {
    let comp: NavigatorUpdateComponent;
    let fixture: ComponentFixture<NavigatorUpdateComponent>;
    let service: NavigatorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [NavigatorUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NavigatorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NavigatorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NavigatorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Navigator(123);
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
        const entity = new Navigator();
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
