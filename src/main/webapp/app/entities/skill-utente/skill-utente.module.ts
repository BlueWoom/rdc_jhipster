import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { SkillUtenteComponent } from './skill-utente.component';
import { SkillUtenteDetailComponent } from './skill-utente-detail.component';
import { SkillUtenteUpdateComponent } from './skill-utente-update.component';
import { SkillUtenteDeleteDialogComponent } from './skill-utente-delete-dialog.component';
import { skillUtenteRoute } from './skill-utente.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(skillUtenteRoute)],
  declarations: [SkillUtenteComponent, SkillUtenteDetailComponent, SkillUtenteUpdateComponent, SkillUtenteDeleteDialogComponent],
  entryComponents: [SkillUtenteDeleteDialogComponent],
})
export class RdcSkillUtenteModule {}
