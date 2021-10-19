import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ICandidato } from 'app/shared/model/candidato.model';

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
  internalUser?: IUser;
  candidatoes?: ICandidato[];
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
    public internalUser?: IUser,
    public candidatoes?: ICandidato[]
  ) {}
}
