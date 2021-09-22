import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAzienda } from 'app/shared/model/azienda.model';

type EntityResponseType = HttpResponse<IAzienda>;
type EntityArrayResponseType = HttpResponse<IAzienda[]>;

@Injectable({ providedIn: 'root' })
export class AziendaService {
  public resourceUrl = SERVER_API_URL + 'api/aziendas';

  constructor(protected http: HttpClient) {}

  create(azienda: IAzienda): Observable<EntityResponseType> {
    return this.http.post<IAzienda>(this.resourceUrl, azienda, { observe: 'response' });
  }

  update(azienda: IAzienda): Observable<EntityResponseType> {
    return this.http.put<IAzienda>(this.resourceUrl, azienda, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAzienda>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAzienda[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
