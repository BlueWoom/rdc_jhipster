import { IIstruzione } from 'app/shared/model/istruzione.model';
import { ICv } from 'app/shared/model/cv.model';

export interface ICvIstruzione {
  id?: number;
  punteggio?: number;
  istruzione?: IIstruzione;
  cv?: ICv;
}

export class CvIstruzione implements ICvIstruzione {
  constructor(public id?: number, public punteggio?: number, public istruzione?: IIstruzione, public cv?: ICv) {}
}
