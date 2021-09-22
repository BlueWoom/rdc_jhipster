import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcJhipsterTestModule } from '../../../test.module';
import { OffertaDetailComponent } from 'app/entities/offerta/offerta-detail.component';
import { Offerta } from 'app/shared/model/offerta.model';

describe('Component Tests', () => {
  describe('Offerta Management Detail Component', () => {
    let comp: OffertaDetailComponent;
    let fixture: ComponentFixture<OffertaDetailComponent>;
    const route = ({ data: of({ offerta: new Offerta(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcJhipsterTestModule],
        declarations: [OffertaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OffertaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OffertaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerta on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerta).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
