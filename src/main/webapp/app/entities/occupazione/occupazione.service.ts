import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOccupazione } from 'app/shared/model/occupazione.model';

type EntityResponseType = HttpResponse<IOccupazione>;
type EntityArrayResponseType = HttpResponse<IOccupazione[]>;

@Injectable({ providedIn: 'root' })
export class OccupazioneService {
  public resourceUrl = SERVER_API_URL + 'api/occupaziones';

  constructor(protected http: HttpClient) {}

  create(occupazione: IOccupazione): Observable<EntityResponseType> {
    return this.http.post<IOccupazione>(this.resourceUrl, occupazione, { observe: 'response' });
  }

  update(occupazione: IOccupazione): Observable<EntityResponseType> {
    return this.http.put<IOccupazione>(this.resourceUrl, occupazione, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOccupazione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOccupazione[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
