import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILogin, Login } from 'app/shared/model/login.model';
import { LoginService } from './login.service';

@Component({
  selector: 'jhi-login-update',
  templateUrl: './login-update.component.html',
})
export class LoginUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    username: [],
    ruolo: [],
  });

  constructor(protected loginService: LoginService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ login }) => {
      this.updateForm(login);
    });
  }

  updateForm(login: ILogin): void {
    this.editForm.patchValue({
      id: login.id,
      username: login.username,
      ruolo: login.ruolo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const login = this.createFromForm();
    if (login.id !== undefined) {
      this.subscribeToSaveResponse(this.loginService.update(login));
    } else {
      this.subscribeToSaveResponse(this.loginService.create(login));
    }
  }

  private createFromForm(): ILogin {
    return {
      ...new Login(),
      id: this.editForm.get(['id'])!.value,
      username: this.editForm.get(['username'])!.value,
      ruolo: this.editForm.get(['ruolo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILogin>>): void {
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
}
