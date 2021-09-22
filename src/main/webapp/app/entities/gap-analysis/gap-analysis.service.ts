import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGapAnalysis } from 'app/shared/model/gap-analysis.model';

type EntityResponseType = HttpResponse<IGapAnalysis>;
type EntityArrayResponseType = HttpResponse<IGapAnalysis[]>;

@Injectable({ providedIn: 'root' })
export class GapAnalysisService {
  public resourceUrl = SERVER_API_URL + 'api/gap-analyses';

  constructor(protected http: HttpClient) {}

  create(gapAnalysis: IGapAnalysis): Observable<EntityResponseType> {
    return this.http.post<IGapAnalysis>(this.resourceUrl, gapAnalysis, { observe: 'response' });
  }

  update(gapAnalysis: IGapAnalysis): Observable<EntityResponseType> {
    return this.http.put<IGapAnalysis>(this.resourceUrl, gapAnalysis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGapAnalysis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGapAnalysis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
