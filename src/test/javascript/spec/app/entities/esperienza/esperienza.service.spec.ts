import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EsperienzaService } from 'app/entities/esperienza/esperienza.service';
import { IEsperienza, Esperienza } from 'app/shared/model/esperienza.model';

describe('Service Tests', () => {
  describe('Esperienza Service', () => {
    let injector: TestBed;
    let service: EsperienzaService;
    let httpMock: HttpTestingController;
    let elemDefault: IEsperienza;
    let expectedResult: IEsperienza | IEsperienza[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EsperienzaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Esperienza(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dal: currentDate.format(DATE_FORMAT),
            al: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Esperienza', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dal: currentDate.format(DATE_FORMAT),
            al: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dal: currentDate,
            al: currentDate,
          },
          returnedFromService
        );

        service.create(new Esperienza()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Esperienza', () => {
        const returnedFromService = Object.assign(
          {
            codice: 'BBBBBB',
            attivita: 'BBBBBB',
            dal: currentDate.format(DATE_FORMAT),
            al: currentDate.format(DATE_FORMAT),
            datoreLavoro: 'BBBBBB',
            sedeLavoro: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dal: currentDate,
            al: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Esperienza', () => {
        const returnedFromService = Object.assign(
          {
            codice: 'BBBBBB',
            attivita: 'BBBBBB',
            dal: currentDate.format(DATE_FORMAT),
            al: currentDate.format(DATE_FORMAT),
            datoreLavoro: 'BBBBBB',
            sedeLavoro: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dal: currentDate,
            al: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Esperienza', () => {
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
