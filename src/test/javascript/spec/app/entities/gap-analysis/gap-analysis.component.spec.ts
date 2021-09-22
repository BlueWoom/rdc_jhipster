import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { GapAnalysisComponent } from 'app/entities/gap-analysis/gap-analysis.component';
import { GapAnalysisService } from 'app/entities/gap-analysis/gap-analysis.service';
import { GapAnalysis } from 'app/shared/model/gap-analysis.model';

describe('Component Tests', () => {
  describe('GapAnalysis Management Component', () => {
    let comp: GapAnalysisComponent;
    let fixture: ComponentFixture<GapAnalysisComponent>;
    let service: GapAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [GapAnalysisComponent],
      })
        .overrideTemplate(GapAnalysisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GapAnalysisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GapAnalysisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GapAnalysis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gapAnalyses && comp.gapAnalyses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
