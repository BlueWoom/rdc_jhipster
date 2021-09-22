import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICvIstruzione } from 'app/shared/model/cv-istruzione.model';

type EntityResponseType = HttpResponse<ICvIstruzione>;
type EntityArrayResponseType = HttpResponse<ICvIstruzione[]>;

@Injectable({ providedIn: 'root' })
export class CvIstruzioneService {
  public resourceUrl = SERVER_API_URL + 'api/cv-istruziones';

  constructor(protected http: HttpClient) {}

  create(cvIstruzione: ICvIstruzione): Observable<EntityResponseType> {
    return this.http.post<ICvIstruzione>(this.resourceUrl, cvIstruzione, { observe: 'response' });
  }

  update(cvIstruzione: ICvIstruzione): Observable<EntityResponseType> {
    return this.http.put<ICvIstruzione>(this.resourceUrl, cvIstruzione, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICvIstruzione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICvIstruzione[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
