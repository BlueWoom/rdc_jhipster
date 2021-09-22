import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AziendaService } from 'app/entities/azienda/azienda.service';
import { IAzienda, Azienda } from 'app/shared/model/azienda.model';

describe('Service Tests', () => {
  describe('Azienda Service', () => {
    let injector: TestBed;
    let service: AziendaService;
    let httpMock: HttpTestingController;
    let elemDefault: IAzienda;
    let expectedResult: IAzienda | IAzienda[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AziendaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Azienda(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Azienda', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Azienda()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Azienda', () => {
        const returnedFromService = Object.assign(
          {
            cf: 'BBBBBB',
            ragioneSociale: 'BBBBBB',
            indirizzoSede: 'BBBBBB',
            provinciaSede: 'BBBBBB',
            ragioneSede: 'BBBBBB',
            capSede: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Azienda', () => {
        const returnedFromService = Object.assign(
          {
            cf: 'BBBBBB',
            ragioneSociale: 'BBBBBB',
            indirizzoSede: 'BBBBBB',
            provinciaSede: 'BBBBBB',
            ragioneSede: 'BBBBBB',
            capSede: 'BBBBBB',
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

      it('should delete a Azienda', () => {
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
