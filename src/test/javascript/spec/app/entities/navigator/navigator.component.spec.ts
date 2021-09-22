import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RdcJhipsterTestModule } from '../../../test.module';
import { NavigatorComponent } from 'app/entities/navigator/navigator.component';
import { NavigatorService } from 'app/entities/navigator/navigator.service';
import { Navigator } from 'app/shared/model/navigator.model';

describe('Component Tests', () => {
  describe('Navigator Management Component', () => {
    let comp: NavigatorComponent;
    let fixture: ComponentFixture<NavigatorComponent>;
    let service: NavigatorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [NavigatorComponent],
      })
        .overrideTemplate(NavigatorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NavigatorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NavigatorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Navigator(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.navigators && comp.navigators[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
