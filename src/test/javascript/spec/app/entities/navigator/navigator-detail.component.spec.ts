import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { NavigatorDetailComponent } from 'app/entities/navigator/navigator-detail.component';
import { Navigator } from 'app/shared/model/navigator.model';

describe('Component Tests', () => {
  describe('Navigator Management Detail Component', () => {
    let comp: NavigatorDetailComponent;
    let fixture: ComponentFixture<NavigatorDetailComponent>;
    const route = ({ data: of({ navigator: new Navigator(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [NavigatorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NavigatorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NavigatorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load navigator on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.navigator).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
