import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { EsperienzaDetailComponent } from 'app/entities/esperienza/esperienza-detail.component';
import { Esperienza } from 'app/shared/model/esperienza.model';

describe('Component Tests', () => {
  describe('Esperienza Management Detail Component', () => {
    let comp: EsperienzaDetailComponent;
    let fixture: ComponentFixture<EsperienzaDetailComponent>;
    const route = ({ data: of({ esperienza: new Esperienza(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [EsperienzaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EsperienzaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EsperienzaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load esperienza on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.esperienza).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
