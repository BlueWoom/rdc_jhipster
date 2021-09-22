export interface IGapAnalysis {
  id?: number;
  codiceEsco?: string;
  nome?: string;
  frequenza?: number;
}

export class GapAnalysis implements IGapAnalysis {
  constructor(public id?: number, public codiceEsco?: string, public nome?: string, public frequenza?: number) {}
}
