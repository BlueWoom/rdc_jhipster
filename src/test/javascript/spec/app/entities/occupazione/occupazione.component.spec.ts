import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

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
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
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

    it('should load a page', () => {
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
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.occupaziones && comp.occupaziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
