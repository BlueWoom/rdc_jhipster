import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { SkillUtenteUpdateComponent } from 'app/entities/skill-utente/skill-utente-update.component';
import { SkillUtenteService } from 'app/entities/skill-utente/skill-utente.service';
import { SkillUtente } from 'app/shared/model/skill-utente.model';

describe('Component Tests', () => {
  describe('SkillUtente Management Update Component', () => {
    let comp: SkillUtenteUpdateComponent;
    let fixture: ComponentFixture<SkillUtenteUpdateComponent>;
    let service: SkillUtenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [SkillUtenteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SkillUtenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillUtenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillUtenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SkillUtente(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SkillUtente();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
