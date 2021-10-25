import { IUser } from 'app/core/user/user.model';
import { IOfferta } from 'app/shared/model/offerta.model';

export interface IAzienda {
  id?: number;
  cf?: string;
  ragioneSociale?: string;
  indirizzoSede?: string;
  provinciaSede?: string;
  regioneSede?: string;
  capSede?: string;
  internalUser?: IUser;
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
    public capSede?: string,
    public internalUser?: IUser,
    public offertas?: IOfferta[]
  ) {}
}
