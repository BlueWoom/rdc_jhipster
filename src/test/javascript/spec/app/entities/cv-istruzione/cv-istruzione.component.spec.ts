import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { CvIstruzioneComponent } from 'app/entities/cv-istruzione/cv-istruzione.component';
import { CvIstruzioneService } from 'app/entities/cv-istruzione/cv-istruzione.service';
import { CvIstruzione } from 'app/shared/model/cv-istruzione.model';

describe('Component Tests', () => {
  describe('CvIstruzione Management Component', () => {
    let comp: CvIstruzioneComponent;
    let fixture: ComponentFixture<CvIstruzioneComponent>;
    let service: CvIstruzioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [CvIstruzioneComponent],
      })
        .overrideTemplate(CvIstruzioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CvIstruzioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CvIstruzioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CvIstruzione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cvIstruziones && comp.cvIstruziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
