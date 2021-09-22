import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INavigator } from 'app/shared/model/navigator.model';

type EntityResponseType = HttpResponse<INavigator>;
type EntityArrayResponseType = HttpResponse<INavigator[]>;

@Injectable({ providedIn: 'root' })
export class NavigatorService {
  public resourceUrl = SERVER_API_URL + 'api/navigators';

  constructor(protected http: HttpClient) {}

  create(navigator: INavigator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(navigator);
    return this.http
      .post<INavigator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(navigator: INavigator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(navigator);
    return this.http
      .put<INavigator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INavigator>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INavigator[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(navigator: INavigator): INavigator {
    const copy: INavigator = Object.assign({}, navigator, {
      dataNascita: navigator.dataNascita && navigator.dataNascita.isValid() ? navigator.dataNascita.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((navigator: INavigator) => {
        navigator.dataNascita = navigator.dataNascita ? moment(navigator.dataNascita) : undefined;
      });
    }
    return res;
  }
}
