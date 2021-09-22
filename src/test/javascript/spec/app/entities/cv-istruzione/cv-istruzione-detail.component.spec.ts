import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { CvIstruzioneDetailComponent } from 'app/entities/cv-istruzione/cv-istruzione-detail.component';
import { CvIstruzione } from 'app/shared/model/cv-istruzione.model';

describe('Component Tests', () => {
  describe('CvIstruzione Management Detail Component', () => {
    let comp: CvIstruzioneDetailComponent;
    let fixture: ComponentFixture<CvIstruzioneDetailComponent>;
    const route = ({ data: of({ cvIstruzione: new CvIstruzione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [CvIstruzioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CvIstruzioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CvIstruzioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cvIstruzione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cvIstruzione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
