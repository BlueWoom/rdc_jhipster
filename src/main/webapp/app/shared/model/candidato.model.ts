import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ISkillUtente } from 'app/shared/model/skill-utente.model';
import { ICv } from 'app/shared/model/cv.model';
import { INavigator } from 'app/shared/model/navigator.model';

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
  internalUser?: IUser;
  skillUtentes?: ISkillUtente[];
  cvs?: ICv[];
  navigator?: INavigator;
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
    public internalUser?: IUser,
    public skillUtentes?: ISkillUtente[],
    public cvs?: ICv[],
    public navigator?: INavigator
  ) {}
}
