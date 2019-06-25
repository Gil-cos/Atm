import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountRegisterComponent } from './account-register/account-register.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserRegisterComponent } from './user-register/user-register.component';

const routes: Routes = [
  {
    path: '',
    component: UserListComponent
  },
  {
    path: 'user-register',
    component: UserRegisterComponent
  },
  {
    path: 'account-register/:userId',
    component: AccountRegisterComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule { }