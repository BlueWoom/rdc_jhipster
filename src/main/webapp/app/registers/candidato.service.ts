import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICandidato } from 'app/shared/model/candidato.model';

type EntityResponseType = HttpResponse<ICandidato>;
type EntityArrayResponseType = HttpResponse<ICandidato[]>;

@Injectable({ providedIn: 'root' })
export class CandidatoService {
  public resourceUrl = SERVER_API_URL + 'api/candidatoes';

  constructor(protected http: HttpClient) {}

  create(candidato: ICandidato): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(candidato);
    return this.http
      .post<ICandidato>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(candidato: ICandidato): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(candidato);
    return this.http
      .put<ICandidato>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICandidato>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICandidato[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(candidato: ICandidato): ICandidato {
    const copy: ICandidato = Object.assign({}, candidato, {
      dataNascita: candidato.dataNascita && candidato.dataNascita.isValid() ? candidato.dataNascita.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataNascita = res.body.dataNascita ? moment(res.body.dataNascita) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((candidato: ICandidato) => {
        candidato.dataNascita = candidato.dataNascita ? moment(candidato.dataNascita) : undefined;
      });
    }
    return res;
  }
}
