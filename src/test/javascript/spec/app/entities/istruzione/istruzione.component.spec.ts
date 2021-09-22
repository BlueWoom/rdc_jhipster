import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcTestModule } from '../../../test.module';
import { IstruzioneComponent } from 'app/entities/istruzione/istruzione.component';
import { IstruzioneService } from 'app/entities/istruzione/istruzione.service';
import { Istruzione } from 'app/shared/model/istruzione.model';

describe('Component Tests', () => {
  describe('Istruzione Management Component', () => {
    let comp: IstruzioneComponent;
    let fixture: ComponentFixture<IstruzioneComponent>;
    let service: IstruzioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [IstruzioneComponent],
      })
        .overrideTemplate(IstruzioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IstruzioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IstruzioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Istruzione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.istruziones && comp.istruziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
