import { INavigator } from 'app/shared/model/navigator.model';
import { IAzienda } from 'app/shared/model/azienda.model';
import { ICandidato } from 'app/shared/model/candidato.model';

export interface ILogin {
  id?: number;
  username?: string;
  ruolo?: string;
  navigators?: INavigator[];
  aziendas?: IAzienda[];
  candidatoes?: ICandidato[];
}

export class Login implements ILogin {
  constructor(
    public id?: number,
    public username?: string,
    public ruolo?: string,
    public navigators?: INavigator[],
    public aziendas?: IAzienda[],
    public candidatoes?: ICandidato[]
  ) {}
}
