import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IAzienda } from 'app/shared/model/azienda.model';

@Injectable({ providedIn: 'root' })
export class RegisterService {
  public resourceUrl = SERVER_API_URL + 'api/registers';

  constructor(protected http: HttpClient) {}

  updateAziendaGeneralInfo(azienda: IAzienda): Observable<HttpResponse<IAzienda>> {
    return this.http.put<IAzienda>(`${this.resourceUrl}/azienda/updateGeneral`, azienda, { observe: 'response' });
  }
}
