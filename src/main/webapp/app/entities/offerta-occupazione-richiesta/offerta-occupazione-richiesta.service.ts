import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

type EntityResponseType = HttpResponse<IOffertaOccupazioneRichiesta>;
type EntityArrayResponseType = HttpResponse<IOffertaOccupazioneRichiesta[]>;

@Injectable({ providedIn: 'root' })
export class OffertaOccupazioneRichiestaService {
  public resourceUrl = SERVER_API_URL + 'api/offerta-occupazione-richiestas';

  constructor(protected http: HttpClient) {}

  create(offertaOccupazioneRichiesta: IOffertaOccupazioneRichiesta): Observable<EntityResponseType> {
    return this.http.post<IOffertaOccupazioneRichiesta>(this.resourceUrl, offertaOccupazioneRichiesta, { observe: 'response' });
  }

  update(offertaOccupazioneRichiesta: IOffertaOccupazioneRichiesta): Observable<EntityResponseType> {
    return this.http.put<IOffertaOccupazioneRichiesta>(this.resourceUrl, offertaOccupazioneRichiesta, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOffertaOccupazioneRichiesta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOffertaOccupazioneRichiesta[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
