import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IAzienda } from 'app/shared/model/azienda.model';
import { IOfferta } from 'app/shared/model/offerta.model';
import { ICandidato } from 'app/shared/model/candidato.model';

@Injectable({ providedIn: 'root' })
export class RegisterService {
  public resourceUrl = SERVER_API_URL + 'api/registers';

  constructor(protected http: HttpClient) {}

  updateAziendaGeneralInfo(azienda: IAzienda): Observable<HttpResponse<IAzienda>> {
    return this.http.put<IAzienda>(`${this.resourceUrl}/azienda/updateGeneral`, azienda, { observe: 'response' });
  }

  updateOffertaGeneralInfo(offerta: IOfferta): Observable<HttpResponse<IOfferta>> {
    return this.http.put<IOfferta>(`${this.resourceUrl}/offerta/updateGeneral`, offerta, { observe: 'response' });
  }

  updateCandidatoGeneralInfo(candidato: ICandidato): Observable<HttpResponse<ICandidato>> {
    return this.http.put<ICandidato>(`${this.resourceUrl}/candidato/updateGeneral`, candidato, { observe: 'response' });
  }
}
