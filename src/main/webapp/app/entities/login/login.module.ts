import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcSharedModule } from 'app/shared/shared.module';
import { LoginComponent } from './login.component';
import { LoginDetailComponent } from './login-detail.component';
import { LoginUpdateComponent } from './login-update.component';
import { LoginDeleteDialogComponent } from './login-delete-dialog.component';
import { loginRoute } from './login.route';

@NgModule({
  imports: [RdcSharedModule, RouterModule.forChild(loginRoute)],
  declarations: [LoginComponent, LoginDetailComponent, LoginUpdateComponent, LoginDeleteDialogComponent],
  entryComponents: [LoginDeleteDialogComponent],
})
export class RdcLoginModule {}
