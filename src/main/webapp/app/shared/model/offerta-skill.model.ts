import { ISkill } from 'app/shared/model/skill.model';
import { IOfferta } from 'app/shared/model/offerta.model';

export interface IOffertaSkill {
  id?: number;
  codiceOfferta?: string;
  codiceEscoSkill?: string;
  optional?: boolean;
  skill?: ISkill;
  offerta?: IOfferta;
}

export class OffertaSkill implements IOffertaSkill {
  constructor(
    public id?: number,
    public codiceOfferta?: string,
    public codiceEscoSkill?: string,
    public optional?: boolean,
    public skill?: ISkill,
    public offerta?: IOfferta
  ) {
    this.optional = this.optional || false;
  }
}
