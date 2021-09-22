import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISkillUtente } from 'app/shared/model/skill-utente.model';

type EntityResponseType = HttpResponse<ISkillUtente>;
type EntityArrayResponseType = HttpResponse<ISkillUtente[]>;

@Injectable({ providedIn: 'root' })
export class SkillUtenteService {
  public resourceUrl = SERVER_API_URL + 'api/skill-utentes';

  constructor(protected http: HttpClient) {}

  create(skillUtente: ISkillUtente): Observable<EntityResponseType> {
    return this.http.post<ISkillUtente>(this.resourceUrl, skillUtente, { observe: 'response' });
  }

  update(skillUtente: ISkillUtente): Observable<EntityResponseType> {
    return this.http.put<ISkillUtente>(this.resourceUrl, skillUtente, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISkillUtente>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISkillUtente[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
