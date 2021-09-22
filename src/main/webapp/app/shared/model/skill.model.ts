import { ISkillUtente } from 'app/shared/model/skill-utente.model';
import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';
import { IOccupazione } from 'app/shared/model/occupazione.model';

export interface ISkill {
  id?: number;
  codiceEsco?: string;
  nome?: string;
  tipo?: string;
  skillUtentes?: ISkillUtente[];
  offertaSkills?: IOffertaSkill[];
  occupaziones?: IOccupazione[];
}

export class Skill implements ISkill {
  constructor(
    public id?: number,
    public codiceEsco?: string,
    public nome?: string,
    public tipo?: string,
    public skillUtentes?: ISkillUtente[],
    public offertaSkills?: IOffertaSkill[],
    public occupaziones?: IOccupazione[]
  ) {}
}
