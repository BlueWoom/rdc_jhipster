import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICandidato, Candidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './../../entities/candidato/candidato.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { INavigator } from 'app/shared/model/navigator.model';
import { NavigatorService } from 'app/entities/navigator/navigator.service';
import { RegisterService } from '../register.service';

import { Provincia } from 'app/shared/enum/provincia.ts';
import { Regione } from 'app/shared/enum/regione.ts';

type SelectableEntity = IUser | INavigator;

@Component({
  selector: 'jhi-candidato-create-update',
  templateUrl: './candidato-create-update.component.html',
})
export class CandidatoCreateUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  navigators: INavigator[] = [];
  dataNascitaDp: any;

  isModified = false;

  tabGeneral = true;

  province = Object.values(Provincia);
  regioni = Object.values(Regione);

  editForm = this.fb.group({
    id: [],
    cf: [],
    nome: [],
    cognome: [],
    dataNascita: [],
    luogoNascita: [],
    sesso: [],
    telefono: [null, [Validators.pattern('[0-9]+')]],
    email: [],
    citta: [],
    indirizzo: [],
    cap: [null, [Validators.pattern('[0-9]+')]],
    provincia: [],
    regione: [],
  });

  constructor(
    protected candidatoService: CandidatoService,
    protected userService: UserService,
    protected navigatorService: NavigatorService,
    protected registerService: RegisterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ candidato }) => {
      this.updateForm(candidato);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.navigatorService.query().subscribe((res: HttpResponse<INavigator[]>) => (this.navigators = res.body || []));
    });
  }

  updateForm(candidato: ICandidato): void {
    this.editForm.patchValue({
      id: candidato.id,
      cf: candidato.cf,
      nome: candidato.nome,
      cognome: candidato.cognome,
      dataNascita: candidato.dataNascita,
      luogoNascita: candidato.luogoNascita,
      sesso: candidato.sesso,
      telefono: candidato.telefono,
      email: candidato.email,
      citta: candidato.citta,
      indirizzo: candidato.indirizzo,
      cap: candidato.cap,
      provincia: candidato.provincia,
      regione: candidato.regione,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const candidato = this.createFromForm();
    if (candidato.id !== undefined) {
      this.subscribeToSaveResponse(this.registerService.updateCandidatoGeneralInfo(candidato));
    } else {
      this.subscribeToSaveResponse(this.candidatoService.create(candidato));
    }
  }

  private createFromForm(): ICandidato {
    return {
      ...new Candidato(),
      id: this.editForm.get(['id'])!.value,
      cf: this.editForm.get(['cf'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      dataNascita: this.editForm.get(['dataNascita'])!.value,
      luogoNascita: this.editForm.get(['luogoNascita'])!.value,
      sesso: this.editForm.get(['sesso'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      email: this.editForm.get(['email'])!.value,
      citta: this.editForm.get(['citta'])!.value,
      indirizzo: this.editForm.get(['indirizzo'])!.value,
      cap: this.editForm.get(['cap'])!.value,
      provincia: this.editForm.get(['provincia'])!.value,
      regione: this.editForm.get(['regione'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICandidato>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.isModified = false;
    this.editForm.markAsPristine();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
