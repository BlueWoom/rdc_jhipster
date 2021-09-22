import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { IstruzioneDetailComponent } from 'app/entities/istruzione/istruzione-detail.component';
import { Istruzione } from 'app/shared/model/istruzione.model';

describe('Component Tests', () => {
  describe('Istruzione Management Detail Component', () => {
    let comp: IstruzioneDetailComponent;
    let fixture: ComponentFixture<IstruzioneDetailComponent>;
    const route = ({ data: of({ istruzione: new Istruzione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [IstruzioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(IstruzioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IstruzioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load istruzione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.istruzione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
