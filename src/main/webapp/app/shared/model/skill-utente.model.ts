import { ISkill } from 'app/shared/model/skill.model';
import { ICandidato } from 'app/shared/model/candidato.model';

export interface ISkillUtente {
  id?: number;
  cfUtente?: string;
  codiceEscoSkill?: string;
  skill?: ISkill;
  candidato?: ICandidato;
}

export class SkillUtente implements ISkillUtente {
  constructor(
    public id?: number,
    public cfUtente?: string,
    public codiceEscoSkill?: string,
    public skill?: ISkill,
    public candidato?: ICandidato
  ) {}
}
