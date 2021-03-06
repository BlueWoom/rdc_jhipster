import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RdcJhipsterSharedModule } from 'app/shared/shared.module';
import { NavigatorComponent } from './navigator.component';
import { NavigatorDetailComponent } from './navigator-detail.component';
import { NavigatorUpdateComponent } from './navigator-update.component';
import { NavigatorDeleteDialogComponent } from './navigator-delete-dialog.component';
import { navigatorRoute } from './navigator.route';

@NgModule({
  imports: [RdcJhipsterSharedModule, RouterModule.forChild(navigatorRoute)],
  declarations: [NavigatorComponent, NavigatorDetailComponent, NavigatorUpdateComponent, NavigatorDeleteDialogComponent],
  entryComponents: [NavigatorDeleteDialogComponent],
})
export class RdcJhipsterNavigatorModule {}
