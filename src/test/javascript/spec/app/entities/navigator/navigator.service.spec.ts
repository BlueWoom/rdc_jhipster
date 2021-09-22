import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { NavigatorService } from 'app/entities/navigator/navigator.service';
import { INavigator, Navigator } from 'app/shared/model/navigator.model';

describe('Service Tests', () => {
  describe('Navigator Service', () => {
    let injector: TestBed;
    let service: NavigatorService;
    let httpMock: HttpTestingController;
    let elemDefault: INavigator;
    let expectedResult: INavigator | INavigator[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NavigatorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Navigator(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataNascita: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Navigator', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataNascita: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.create(new Navigator()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Navigator', () => {
        const returnedFromService = Object.assign(
          {
            cf: 'BBBBBB',
            nome: 'BBBBBB',
            cognome: 'BBBBBB',
            dataNascita: currentDate.format(DATE_FORMAT),
            sesso: 'BBBBBB',
            telefono: 'BBBBBB',
            email: 'BBBBBB',
            citta: 'BBBBBB',
            indirizzo: 'BBBBBB',
            cap: 'BBBBBB',
            provincia: 'BBBBBB',
            regione: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Navigator', () => {
        const returnedFromService = Object.assign(
          {
            cf: 'BBBBBB',
            nome: 'BBBBBB',
            cognome: 'BBBBBB',
            dataNascita: currentDate.format(DATE_FORMAT),
            sesso: 'BBBBBB',
            telefono: 'BBBBBB',
            email: 'BBBBBB',
            citta: 'BBBBBB',
            indirizzo: 'BBBBBB',
            cap: 'BBBBBB',
            provincia: 'BBBBBB',
            regione: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Navigator', () => {
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
