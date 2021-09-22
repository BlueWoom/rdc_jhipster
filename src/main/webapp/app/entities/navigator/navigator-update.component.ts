import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INavigator, Navigator } from 'app/shared/model/navigator.model';
import { NavigatorService } from './navigator.service';
import { ILogin } from 'app/shared/model/login.model';
import { LoginService } from 'app/entities/login/login.service';

@Component({
  selector: 'jhi-navigator-update',
  templateUrl: './navigator-update.component.html',
})
export class NavigatorUpdateComponent implements OnInit {
  isSaving = false;
  logins: ILogin[] = [];
  dataNascitaDp: any;

  editForm = this.fb.group({
    id: [],
    cf: [],
    nome: [],
    cognome: [],
    dataNascita: [],
    sesso: [],
    telefono: [null, [Validators.pattern('[0-9]+')]],
    email: [],
    citta: [],
    indirizzo: [],
    cap: [null, [Validators.pattern('[0-9]+')]],
    provincia: [],
    regione: [],
    login: [],
  });

  constructor(
    protected navigatorService: NavigatorService,
    protected loginService: LoginService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ navigator }) => {
      this.updateForm(navigator);

      this.loginService.query().subscribe((res: HttpResponse<ILogin[]>) => (this.logins = res.body || []));
    });
  }

  updateForm(navigator: INavigator): void {
    this.editForm.patchValue({
      id: navigator.id,
      cf: navigator.cf,
      nome: navigator.nome,
      cognome: navigator.cognome,
      dataNascita: navigator.dataNascita,
      sesso: navigator.sesso,
      telefono: navigator.telefono,
      email: navigator.email,
      citta: navigator.citta,
      indirizzo: navigator.indirizzo,
      cap: navigator.cap,
      provincia: navigator.provincia,
      regione: navigator.regione,
      login: navigator.login,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const navigator = this.createFromForm();
    if (navigator.id !== undefined) {
      this.subscribeToSaveResponse(this.navigatorService.update(navigator));
    } else {
      this.subscribeToSaveResponse(this.navigatorService.create(navigator));
    }
  }

  private createFromForm(): INavigator {
    return {
      ...new Navigator(),
      id: this.editForm.get(['id'])!.value,
      cf: this.editForm.get(['cf'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      dataNascita: this.editForm.get(['dataNascita'])!.value,
      sesso: this.editForm.get(['sesso'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      email: this.editForm.get(['email'])!.value,
      citta: this.editForm.get(['citta'])!.value,
      indirizzo: this.editForm.get(['indirizzo'])!.value,
      cap: this.editForm.get(['cap'])!.value,
      provincia: this.editForm.get(['provincia'])!.value,
      regione: this.editForm.get(['regione'])!.value,
      login: this.editForm.get(['login'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INavigator>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILogin): any {
    return item.id;
  }
}
