import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIstruzione } from 'app/shared/model/istruzione.model';

type EntityResponseType = HttpResponse<IIstruzione>;
type EntityArrayResponseType = HttpResponse<IIstruzione[]>;

@Injectable({ providedIn: 'root' })
export class IstruzioneService {
  public resourceUrl = SERVER_API_URL + 'api/istruziones';

  constructor(protected http: HttpClient) {}

  create(istruzione: IIstruzione): Observable<EntityResponseType> {
    return this.http.post<IIstruzione>(this.resourceUrl, istruzione, { observe: 'response' });
  }

  update(istruzione: IIstruzione): Observable<EntityResponseType> {
    return this.http.put<IIstruzione>(this.resourceUrl, istruzione, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIstruzione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIstruzione[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
