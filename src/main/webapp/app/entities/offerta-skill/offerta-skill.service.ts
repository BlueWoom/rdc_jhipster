import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOffertaSkill } from 'app/shared/model/offerta-skill.model';

type EntityResponseType = HttpResponse<IOffertaSkill>;
type EntityArrayResponseType = HttpResponse<IOffertaSkill[]>;

@Injectable({ providedIn: 'root' })
export class OffertaSkillService {
  public resourceUrl = SERVER_API_URL + 'api/offerta-skills';

  constructor(protected http: HttpClient) {}

  create(offertaSkill: IOffertaSkill): Observable<EntityResponseType> {
    return this.http.post<IOffertaSkill>(this.resourceUrl, offertaSkill, { observe: 'response' });
  }

  update(offertaSkill: IOffertaSkill): Observable<EntityResponseType> {
    return this.http.put<IOffertaSkill>(this.resourceUrl, offertaSkill, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOffertaSkill>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOffertaSkill[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
