import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'azienda',
        loadChildren: () => import('./azienda/azienda.module').then(m => m.RdcAziendaModule),
      },
      {
        path: 'candidato',
        loadChildren: () => import('./candidato/candidato.module').then(m => m.RdcCandidatoModule),
      },
      {
        path: 'esperienza',
        loadChildren: () => import('./esperienza/esperienza.module').then(m => m.RdcEsperienzaModule),
      },
      {
        path: 'gap-analysis',
        loadChildren: () => import('./gap-analysis/gap-analysis.module').then(m => m.RdcGapAnalysisModule),
      },
      {
        path: 'istruzione',
        loadChildren: () => import('./istruzione/istruzione.module').then(m => m.RdcIstruzioneModule),
      },
      {
        path: 'navigator',
        loadChildren: () => import('./navigator/navigator.module').then(m => m.RdcNavigatorModule),
      },
      {
        path: 'login',
        loadChildren: () => import('./login/login.module').then(m => m.RdcLoginModule),
      },
      {
        path: 'occupazione',
        loadChildren: () => import('./occupazione/occupazione.module').then(m => m.RdcOccupazioneModule),
      },
      {
        path: 'offerta',
        loadChildren: () => import('./offerta/offerta.module').then(m => m.RdcOffertaModule),
      },
      {
        path: 'offerta-skill',
        loadChildren: () => import('./offerta-skill/offerta-skill.module').then(m => m.RdcOffertaSkillModule),
      },
      {
        path: 'skill',
        loadChildren: () => import('./skill/skill.module').then(m => m.RdcSkillModule),
      },
      {
        path: 'cv',
        loadChildren: () => import('./cv/cv.module').then(m => m.RdcCvModule),
      },
      {
        path: 'cv-istruzione',
        loadChildren: () => import('./cv-istruzione/cv-istruzione.module').then(m => m.RdcCvIstruzioneModule),
      },
      {
        path: 'skill-utente',
        loadChildren: () => import('./skill-utente/skill-utente.module').then(m => m.RdcSkillUtenteModule),
      },
      {
        path: 'offerta-occupazione-richiesta',
        loadChildren: () =>
          import('./offerta-occupazione-richiesta/offerta-occupazione-richiesta.module').then(m => m.RdcOffertaOccupazioneRichiestaModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class RdcEntityModule {}
