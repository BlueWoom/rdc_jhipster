import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OffertaService } from 'app/entities/offerta/offerta.service';
import { IOfferta, Offerta } from 'app/shared/model/offerta.model';

describe('Service Tests', () => {
  describe('Offerta Service', () => {
    let injector: TestBed;
    let service: OffertaService;
    let httpMock: HttpTestingController;
    let elemDefault: IOfferta;
    let expectedResult: IOfferta | IOfferta[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OffertaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Offerta(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            data: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Offerta', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            data: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate,
          },
          returnedFromService
        );

        service.create(new Offerta()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Offerta', () => {
        const returnedFromService = Object.assign(
          {
            data: currentDate.format(DATE_FORMAT),
            indirizzoSede: 'BBBBBB',
            cittaSede: 'BBBBBB',
            capSede: 'BBBBBB',
            provinciaSede: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Offerta', () => {
        const returnedFromService = Object.assign(
          {
            data: currentDate.format(DATE_FORMAT),
            indirizzoSede: 'BBBBBB',
            cittaSede: 'BBBBBB',
            capSede: 'BBBBBB',
            provinciaSede: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Offerta', () => {
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
