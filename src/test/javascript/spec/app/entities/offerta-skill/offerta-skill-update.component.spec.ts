import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RdcTestModule } from '../../../test.module';
import { OffertaSkillUpdateComponent } from 'app/entities/offerta-skill/offerta-skill-update.component';
import { OffertaSkillService } from 'app/entities/offerta-skill/offerta-skill.service';
import { OffertaSkill } from 'app/shared/model/offerta-skill.model';

describe('Component Tests', () => {
  describe('OffertaSkill Management Update Component', () => {
    let comp: OffertaSkillUpdateComponent;
    let fixture: ComponentFixture<OffertaSkillUpdateComponent>;
    let service: OffertaSkillService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RdcTestModule],
        declarations: [OffertaSkillUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OffertaSkillUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffertaSkillUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffertaSkillService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OffertaSkill(123);
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
        const entity = new OffertaSkill();
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
