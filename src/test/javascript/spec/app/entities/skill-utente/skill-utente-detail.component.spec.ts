import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { SkillUtenteDetailComponent } from 'app/entities/skill-utente/skill-utente-detail.component';
import { SkillUtente } from 'app/shared/model/skill-utente.model';

describe('Component Tests', () => {
  describe('SkillUtente Management Detail Component', () => {
    let comp: SkillUtenteDetailComponent;
    let fixture: ComponentFixture<SkillUtenteDetailComponent>;
    const route = ({ data: of({ skillUtente: new SkillUtente(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [SkillUtenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SkillUtenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SkillUtenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load skillUtente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.skillUtente).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
