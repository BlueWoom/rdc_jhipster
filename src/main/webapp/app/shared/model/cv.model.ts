import { Moment } from 'moment';
import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';
import { IEsperienza } from 'app/shared/model/esperienza.model';
import { ICandidato } from 'app/shared/model/candidato.model';

export interface ICv {
  id?: number;
  inserimento?: Moment;
  cvIstruziones?: ICvIstruzione[];
  esperienzas?: IEsperienza[];
  candidato?: ICandidato;
}

export class Cv implements ICv {
  constructor(
    public id?: number,
    public inserimento?: Moment,
    public cvIstruziones?: ICvIstruzione[],
    public esperienzas?: IEsperienza[],
    public candidato?: ICandidato
  ) {}
}
