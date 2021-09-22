import { Moment } from 'moment';
import { ICv } from 'app/shared/model/cv.model';
import { IOccupazione } from 'app/shared/model/occupazione.model';

export interface IEsperienza {
  id?: number;
  codice?: string;
  attivita?: string;
  dal?: Moment;
  al?: Moment;
  datoreLavoro?: string;
  sedeLavoro?: string;
  cv?: ICv;
  occupaziones?: IOccupazione[];
}

export class Esperienza implements IEsperienza {
  constructor(
    public id?: number,
    public codice?: string,
    public attivita?: string,
    public dal?: Moment,
    public al?: Moment,
    public datoreLavoro?: string,
    public sedeLavoro?: string,
    public cv?: ICv,
    public occupaziones?: IOccupazione[]
  ) {}
}
