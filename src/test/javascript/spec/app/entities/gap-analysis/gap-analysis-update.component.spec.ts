import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { GapAnalysisUpdateComponent } from 'app/entities/gap-analysis/gap-analysis-update.component';
import { GapAnalysisService } from 'app/entities/gap-analysis/gap-analysis.service';
import { GapAnalysis } from 'app/shared/model/gap-analysis.model';

describe('Component Tests', () => {
  describe('GapAnalysis Management Update Component', () => {
    let comp: GapAnalysisUpdateComponent;
    let fixture: ComponentFixture<GapAnalysisUpdateComponent>;
    let service: GapAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [GapAnalysisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GapAnalysisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GapAnalysisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GapAnalysisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GapAnalysis(123);
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
        const entity = new GapAnalysis();
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
