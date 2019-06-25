import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRegisterComponent } from './user-register/user-register.component';
import { AccountRegisterComponent } from './account-register/account-register.component';
import { UserListComponent } from './user-list/user-list.component';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { UserRegisterService } from './user-register/user-register.service';
import { AdminRoutingModule } from './admin-routing.module';

@NgModule({
  declarations: [
    UserRegisterComponent,
    AccountRegisterComponent,
    UserListComponent
  ],
  exports: [
    UserRegisterComponent,
    AccountRegisterComponent,
    UserListComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ],
  providers: [
    UserRegisterService
  ]
})
export class AdminModule { }
