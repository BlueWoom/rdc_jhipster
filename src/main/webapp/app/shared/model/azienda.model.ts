import { IOfferta } from 'app/shared/model/offerta.model';
import { ILogin } from 'app/shared/model/login.model';

export interface IAzienda {
  id?: number;
  cf?: string;
  ragioneSociale?: string;
  indirizzoSede?: string;
  provinciaSede?: string;
  ragioneSede?: string;
  capSede?: string;
  offertas?: IOfferta[];
  login?: ILogin;
}

export class Azienda implements IAzienda {
  constructor(
    public id?: number,
    public cf?: string,
    public ragioneSociale?: string,
    public indirizzoSede?: string,
    public provinciaSede?: string,
    public ragioneSede?: string,
    public capSede?: string,
    public offertas?: IOfferta[],
    public login?: ILogin
  ) {}
}
