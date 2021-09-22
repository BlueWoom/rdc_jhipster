import { IOfferta } from 'app/shared/model/offerta.model';
import { IOccupazione } from 'app/shared/model/occupazione.model';

export interface IOffertaOccupazioneRichiesta {
  id?: number;
  codiceOfferta?: string;
  codiceEscoOccupazione?: string;
  anni?: number;
  offerta?: IOfferta;
  occupazione?: IOccupazione;
}

export class OffertaOccupazioneRichiesta implements IOffertaOccupazioneRichiesta {
  constructor(
    public id?: number,
    public codiceOfferta?: string,
    public codiceEscoOccupazione?: string,
    public anni?: number,
    public offerta?: IOfferta,
    public occupazione?: IOccupazione
  ) {}
}
