import { Moment } from 'moment';
import { ISkillUtente } from 'app/shared/model/skill-utente.model';
import { ICv } from 'app/shared/model/cv.model';
import { INavigator } from 'app/shared/model/navigator.model';
import { ILogin } from 'app/shared/model/login.model';

export interface ICandidato {
  id?: number;
  cf?: string;
  nome?: string;
  cognome?: string;
  dataNascita?: Moment;
  luogoNascita?: string;
  sesso?: string;
  telefono?: string;
  email?: string;
  citta?: string;
  indirizzo?: string;
  cap?: string;
  provincia?: string;
  regione?: string;
  skillUtentes?: ISkillUtente[];
  cvs?: ICv[];
  navigator?: INavigator;
  login?: ILogin;
}

export class Candidato implements ICandidato {
  constructor(
    public id?: number,
    public cf?: string,
    public nome?: string,
    public cognome?: string,
    public dataNascita?: Moment,
    public luogoNascita?: string,
    public sesso?: string,
    public telefono?: string,
    public email?: string,
    public citta?: string,
    public indirizzo?: string,
    public cap?: string,
    public provincia?: string,
    public regione?: string,
    public skillUtentes?: ISkillUtente[],
    public cvs?: ICv[],
    public navigator?: INavigator,
    public login?: ILogin
  ) {}
}
