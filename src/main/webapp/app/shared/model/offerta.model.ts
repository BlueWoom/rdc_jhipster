import { Moment } from 'moment';
import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';
import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';
import { IIstruzione } from 'app/shared/model/istruzione.model';
import { IAzienda } from 'app/shared/model/azienda.model';
import { IOccupazione } from 'app/shared/model/occupazione.model';

export interface IOfferta {
  id?: number;
  data?: Moment;
  indirizzoSede?: string;
  cittaSede?: string;
  capSede?: string;
  provinciaSede?: string;
  offertaSkills?: IOffertaSkill[];
  offertaOccupazioneRichiestas?: IOffertaOccupazioneRichiesta[];
  istruzione?: IIstruzione;
  azienda?: IAzienda;
  occupaziones?: IOccupazione[];
}

export class Offerta implements IOfferta {
  constructor(
    public id?: number,
    public data?: Moment,
    public indirizzoSede?: string,
    public cittaSede?: string,
    public capSede?: string,
    public provinciaSede?: string,
    public offertaSkills?: IOffertaSkill[],
    public offertaOccupazioneRichiestas?: IOffertaOccupazioneRichiesta[],
    public istruzione?: IIstruzione,
    public azienda?: IAzienda,
    public occupaziones?: IOccupazione[]
  ) {}
}
