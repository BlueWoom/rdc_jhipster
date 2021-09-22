import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { IEsperienza } from 'app/shared/model/esperienza.model';
import { IOfferta } from 'app/shared/model/offerta.model';
import { ISkill } from 'app/shared/model/skill.model';

export interface IOccupazione {
  id?: number;
  codiceEsco?: string;
  nome?: string;
  offertaOccupazioneRichiestas?: IOffertaOccupazioneRichiesta[];
  esperienzas?: IEsperienza[];
  offertas?: IOfferta[];
  skills?: ISkill[];
}

export class Occupazione implements IOccupazione {
  constructor(
    public id?: number,
    public codiceEsco?: string,
    public nome?: string,
    public offertaOccupazioneRichiestas?: IOffertaOccupazioneRichiesta[],
    public esperienzas?: IEsperienza[],
    public offertas?: IOfferta[],
    public skills?: ISkill[]
  ) {}
}
