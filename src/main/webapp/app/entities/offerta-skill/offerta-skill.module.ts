import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { OffertaSkillComponent } from './offerta-skill.component';
import { OffertaSkillDetailComponent } from './offerta-skill-detail.component';
import { OffertaSkillUpdateComponent } from './offerta-skill-update.component';
import { OffertaSkillDeleteDialogComponent } from './offerta-skill-delete-dialog.component';
import { offertaSkillRoute } from './offerta-skill.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(offertaSkillRoute)],
  declarations: [OffertaSkillComponent, OffertaSkillDetailComponent, OffertaSkillUpdateComponent, OffertaSkillDeleteDialogComponent],
  entryComponents: [OffertaSkillDeleteDialogComponent],
})
export class RdcJhipsterOffertaSkillModule {}
