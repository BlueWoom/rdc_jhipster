import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { CvDetailComponent } from 'app/entities/cv/cv-detail.component';
import { Cv } from 'app/shared/model/cv.model';

describe('Component Tests', () => {
  describe('Cv Management Detail Component', () => {
    let comp: CvDetailComponent;
    let fixture: ComponentFixture<CvDetailComponent>;
    const route = ({ data: of({ cv: new Cv(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [CvDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CvDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CvDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cv on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cv).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
