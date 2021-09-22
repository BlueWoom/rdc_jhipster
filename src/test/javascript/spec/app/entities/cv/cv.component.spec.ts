import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { CvComponent } from 'app/entities/cv/cv.component';
import { CvService } from 'app/entities/cv/cv.service';
import { Cv } from 'app/shared/model/cv.model';

describe('Component Tests', () => {
  describe('Cv Management Component', () => {
    let comp: CvComponent;
    let fixture: ComponentFixture<CvComponent>;
    let service: CvService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [CvComponent],
      })
        .overrideTemplate(CvComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CvComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CvService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Cv(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cvs && comp.cvs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
