import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserRoutingModule } from './user-routing.module';
import { AccountService } from './account.service';

@NgModule({
  declarations: [
    UserDetailComponent
  ],
  exports: [
    UserDetailComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    UserRoutingModule
  ],
  providers: [
    AccountService
  ]
})
export class UserModule { }
