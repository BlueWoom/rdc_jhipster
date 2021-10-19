import { IOfferta } from 'app/shared/model/offerta.model';
import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';

export interface IIstruzione {
  id?: number;
  codiceIsced?: string;
  codiceLivello?: string;
  nome?: string;
  campoStudio?: string;
  sinonimi?: string;
  codiceIstituto?: string;
  tipoIstituto?: string;
  offertas?: IOfferta[];
  cvIstruziones?: ICvIstruzione[];
}

export class Istruzione implements IIstruzione {
  constructor(
    public id?: number,
    public codiceIsced?: string,
    public codiceLivello?: string,
    public nome?: string,
    public campoStudio?: string,
    public sinonimi?: string,
    public codiceIstituto?: string,
    public tipoIstituto?: string,
    public offertas?: IOfferta[],
    public cvIstruziones?: ICvIstruzione[]
  ) {}
}
