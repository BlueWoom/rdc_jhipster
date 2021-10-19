import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'azienda',
        loadChildren: () => import('./azienda/azienda.module').then(m => m.RdcJhipsterAziendaModule),
      },
      {
        path: 'candidato',
        loadChildren: () => import('./candidato/candidato.module').then(m => m.RdcJhipsterCandidatoModule),
      },
      {
        path: 'esperienza',
        loadChildren: () => import('./esperienza/esperienza.module').then(m => m.RdcJhipsterEsperienzaModule),
      },
      {
        path: 'gap-analysis',
        loadChildren: () => import('./gap-analysis/gap-analysis.module').then(m => m.RdcJhipsterGapAnalysisModule),
      },
      {
        path: 'istruzione',
        loadChildren: () => import('./istruzione/istruzione.module').then(m => m.RdcJhipsterIstruzioneModule),
      },
      {
        path: 'navigator',
        loadChildren: () => import('./navigator/navigator.module').then(m => m.RdcJhipsterNavigatorModule),
      },
      {
        path: 'occupazione',
        loadChildren: () => import('./occupazione/occupazione.module').then(m => m.RdcJhipsterOccupazioneModule),
      },
      {
        path: 'offerta',
        loadChildren: () => import('./offerta/offerta.module').then(m => m.RdcJhipsterOffertaModule),
      },
      {
        path: 'offerta-skill',
        loadChildren: () => import('./offerta-skill/offerta-skill.module').then(m => m.RdcJhipsterOffertaSkillModule),
      },
      {
        path: 'skill',
        loadChildren: () => import('./skill/skill.module').then(m => m.RdcJhipsterSkillModule),
      },
      {
        path: 'cv',
        loadChildren: () => import('./cv/cv.module').then(m => m.RdcJhipsterCvModule),
      },
      {
        path: 'cv-istruzione',
        loadChildren: () => import('./cv-istruzione/cv-istruzione.module').then(m => m.RdcJhipsterCvIstruzioneModule),
      },
      {
        path: 'skill-utente',
        loadChildren: () => import('./skill-utente/skill-utente.module').then(m => m.RdcJhipsterSkillUtenteModule),
      },
      {
        path: 'offerta-occupazione-richiesta',
        loadChildren: () =>
          import('./offerta-occupazione-richiesta/offerta-occupazione-richiesta.module').then(
            m => m.RdcJhipsterOffertaOccupazioneRichiestaModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class RdcJhipsterEntityModule {}
