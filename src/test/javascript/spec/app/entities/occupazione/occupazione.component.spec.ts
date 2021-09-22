import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OccupazioneComponent } from 'app/entities/occupazione/occupazione.component';
import { OccupazioneService } from 'app/entities/occupazione/occupazione.service';
import { Occupazione } from 'app/shared/model/occupazione.model';

describe('Component Tests', () => {
  describe('Occupazione Management Component', () => {
    let comp: OccupazioneComponent;
    let fixture: ComponentFixture<OccupazioneComponent>;
    let service: OccupazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OccupazioneComponent],
      })
        .overrideTemplate(OccupazioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OccupazioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OccupazioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Occupazione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.occupaziones && comp.occupaziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
