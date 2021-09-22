import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { GapAnalysisDetailComponent } from 'app/entities/gap-analysis/gap-analysis-detail.component';
import { GapAnalysis } from 'app/shared/model/gap-analysis.model';

describe('Component Tests', () => {
  describe('GapAnalysis Management Detail Component', () => {
    let comp: GapAnalysisDetailComponent;
    let fixture: ComponentFixture<GapAnalysisDetailComponent>;
    const route = ({ data: of({ gapAnalysis: new GapAnalysis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [GapAnalysisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GapAnalysisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GapAnalysisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gapAnalysis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gapAnalysis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
