import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OffertaOccupazioneRichiestaService } from 'app/entities/offerta-occupazione-richiesta/offerta-occupazione-richiesta.service';
import { IOffertaOccupazioneRichiesta, OffertaOccupazioneRichiesta } from 'app/shared/model/offerta-occupazione-richiesta.model';

describe('Service Tests', () => {
  describe('OffertaOccupazioneRichiesta Service', () => {
    let injector: TestBed;
    let service: OffertaOccupazioneRichiestaService;
    let httpMock: HttpTestingController;
    let elemDefault: IOffertaOccupazioneRichiesta;
    let expectedResult: IOffertaOccupazioneRichiesta | IOffertaOccupazioneRichiesta[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OffertaOccupazioneRichiestaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OffertaOccupazioneRichiesta(0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OffertaOccupazioneRichiesta', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OffertaOccupazioneRichiesta()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OffertaOccupazioneRichiesta', () => {
        const returnedFromService = Object.assign(
          {
            anni: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OffertaOccupazioneRichiesta', () => {
        const returnedFromService = Object.assign(
          {
            anni: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OffertaOccupazioneRichiesta', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
