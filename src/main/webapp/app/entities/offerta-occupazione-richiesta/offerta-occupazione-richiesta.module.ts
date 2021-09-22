import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { OffertaOccupazioneRichiestaComponent } from './offerta-occupazione-richiesta.component';
import { OffertaOccupazioneRichiestaDetailComponent } from './offerta-occupazione-richiesta-detail.component';
import { OffertaOccupazioneRichiestaUpdateComponent } from './offerta-occupazione-richiesta-update.component';
import { OffertaOccupazioneRichiestaDeleteDialogComponent } from './offerta-occupazione-richiesta-delete-dialog.component';
import { offertaOccupazioneRichiestaRoute } from './offerta-occupazione-richiesta.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(offertaOccupazioneRichiestaRoute)],
  declarations: [
    OffertaOccupazioneRichiestaComponent,
    OffertaOccupazioneRichiestaDetailComponent,
    OffertaOccupazioneRichiestaUpdateComponent,
    OffertaOccupazioneRichiestaDeleteDialogComponent,
  ],
  entryComponents: [OffertaOccupazioneRichiestaDeleteDialogComponent],
})
export class RdcJhipsterOffertaOccupazioneRichiestaModule {}
