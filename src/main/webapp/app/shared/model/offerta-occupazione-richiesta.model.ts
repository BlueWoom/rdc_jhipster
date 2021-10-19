import { IOfferta } from 'app/shared/model/offerta.model';
import { IOccupazione } from 'app/shared/model/occupazione.model';

export interface IOffertaOccupazioneRichiesta {
  id?: number;
  anni?: number;
  offerta?: IOfferta;
  occupazione?: IOccupazione;
}

export class OffertaOccupazioneRichiesta implements IOffertaOccupazioneRichiesta {
  constructor(public id?: number, public anni?: number, public offerta?: IOfferta, public occupazione?: IOccupazione) {}
}
