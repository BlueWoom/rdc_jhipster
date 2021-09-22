import { Moment } from 'moment';
import { ICandidato } from 'app/shared/model/candidato.model';
import { ILogin } from 'app/shared/model/login.model';

export interface INavigator {
  id?: number;
  cf?: string;
  nome?: string;
  cognome?: string;
  dataNascita?: Moment;
  sesso?: string;
  telefono?: string;
  email?: string;
  citta?: string;
  indirizzo?: string;
  cap?: string;
  provincia?: string;
  regione?: string;
  candidatoes?: ICandidato[];
  login?: ILogin;
}

export class Navigator implements INavigator {
  constructor(
    public id?: number,
    public cf?: string,
    public nome?: string,
    public cognome?: string,
    public dataNascita?: Moment,
    public sesso?: string,
    public telefono?: string,
    public email?: string,
    public citta?: string,
    public indirizzo?: string,
    public cap?: string,
    public provincia?: string,
    public regione?: string,
    public candidatoes?: ICandidato[],
    public login?: ILogin
  ) {}
}
