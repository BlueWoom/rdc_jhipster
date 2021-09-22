import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { AziendaDetailComponent } from 'app/entities/azienda/azienda-detail.component';
import { Azienda } from 'app/shared/model/azienda.model';

describe('Component Tests', () => {
  describe('Azienda Management Detail Component', () => {
    let comp: AziendaDetailComponent;
    let fixture: ComponentFixture<AziendaDetailComponent>;
    const route = ({ data: of({ azienda: new Azienda(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [AziendaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AziendaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AziendaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load azienda on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.azienda).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
