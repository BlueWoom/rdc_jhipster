import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEsperienza } from 'app/shared/model/esperienza.model';

type EntityResponseType = HttpResponse<IEsperienza>;
type EntityArrayResponseType = HttpResponse<IEsperienza[]>;

@Injectable({ providedIn: 'root' })
export class EsperienzaService {
  public resourceUrl = SERVER_API_URL + 'api/esperienzas';

  constructor(protected http: HttpClient) {}

  create(esperienza: IEsperienza): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(esperienza);
    return this.http
      .post<IEsperienza>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(esperienza: IEsperienza): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(esperienza);
    return this.http
      .put<IEsperienza>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEsperienza>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEsperienza[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(esperienza: IEsperienza): IEsperienza {
    const copy: IEsperienza = Object.assign({}, esperienza, {
      dal: esperienza.dal && esperienza.dal.isValid() ? esperienza.dal.format(DATE_FORMAT) : undefined,
      al: esperienza.al && esperienza.al.isValid() ? esperienza.al.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dal = res.body.dal ? moment(res.body.dal) : undefined;
      res.body.al = res.body.al ? moment(res.body.al) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((esperienza: IEsperienza) => {
        esperienza.dal = esperienza.dal ? moment(esperienza.dal) : undefined;
        esperienza.al = esperienza.al ? moment(esperienza.al) : undefined;
      });
    }
    return res;
  }
}
