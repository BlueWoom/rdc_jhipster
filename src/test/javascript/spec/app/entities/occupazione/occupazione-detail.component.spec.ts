import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { OccupazioneDetailComponent } from 'app/entities/occupazione/occupazione-detail.component';
import { Occupazione } from 'app/shared/model/occupazione.model';

describe('Component Tests', () => {
  describe('Occupazione Management Detail Component', () => {
    let comp: OccupazioneDetailComponent;
    let fixture: ComponentFixture<OccupazioneDetailComponent>;
    const route = ({ data: of({ occupazione: new Occupazione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OccupazioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OccupazioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OccupazioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load occupazione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.occupazione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
