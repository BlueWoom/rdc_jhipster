import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferta } from 'app/shared/model/offerta.model';

type EntityResponseType = HttpResponse<IOfferta>;
type EntityArrayResponseType = HttpResponse<IOfferta[]>;

@Injectable({ providedIn: 'root' })
export class OffertaService {
  public resourceUrl = SERVER_API_URL + 'api/offertas';

  constructor(protected http: HttpClient) {}

  create(offerta: IOfferta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerta);
    return this.http
      .post<IOfferta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerta: IOfferta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerta);
    return this.http
      .put<IOfferta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOfferta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerta: IOfferta): IOfferta {
    const copy: IOfferta = Object.assign({}, offerta, {
      data: offerta.data && offerta.data.isValid() ? offerta.data.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((offerta: IOfferta) => {
        offerta.data = offerta.data ? moment(offerta.data) : undefined;
      });
    }
    return res;
  }
}
