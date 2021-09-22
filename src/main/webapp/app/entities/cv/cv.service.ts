import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICv } from 'app/shared/model/cv.model';

type EntityResponseType = HttpResponse<ICv>;
type EntityArrayResponseType = HttpResponse<ICv[]>;

@Injectable({ providedIn: 'root' })
export class CvService {
  public resourceUrl = SERVER_API_URL + 'api/cvs';

  constructor(protected http: HttpClient) {}

  create(cv: ICv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cv);
    return this.http
      .post<ICv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cv: ICv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cv);
    return this.http
      .put<ICv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICv>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICv[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cv: ICv): ICv {
    const copy: ICv = Object.assign({}, cv, {
      inserimento: cv.inserimento && cv.inserimento.isValid() ? cv.inserimento.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.inserimento = res.body.inserimento ? moment(res.body.inserimento) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cv: ICv) => {
        cv.inserimento = cv.inserimento ? moment(cv.inserimento) : undefined;
      });
    }
    return res;
  }
}
