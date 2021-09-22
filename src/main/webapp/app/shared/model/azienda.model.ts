import { IOfferta } from 'app/shared/model/offerta.model';

export interface IAzienda {
  id?: number;
  cf?: string;
  ragioneSociale?: string;
  indirizzoSede?: string;
  provinciaSede?: string;
  ragioneSede?: string;
  cittaSede?: string;
  capSede?: string;
  offertas?: IOfferta[];
}

export class Azienda implements IAzienda {
  constructor(
    public id?: number,
    public cf?: string,
    public ragioneSociale?: string,
    public indirizzoSede?: string,
    public provinciaSede?: string,
    public ragioneSede?: string,
    public cittaSede?: string,
    public capSede?: string,
    public offertas?: IOfferta[]
  ) {}
}
