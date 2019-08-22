import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AccountForm } from 'src/app/core/model/AccountForm';
import { NewUser } from 'src/app/core/model/NewUser';
import { environment } from 'src/environments/environment';
import { Page } from '../core/model/page';
import { UserDto } from '../core/model/UserDto';

const API = environment.ApiUrl;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AdminService {

  constructor(
    private http: HttpClient
  ) { }

  registerUser(user: NewUser) {
    const body = JSON.stringify(user);
    return this.http.post(`${API}/api/user`, body, httpOptions);
  }

  registerAccount(account: AccountForm, userId: number) {
    const body = JSON.stringify(account);
    return this.http.post(`${API}/api/account/${userId}`, body, httpOptions);
  }

  getUsers(page: number, size: number) {
    return this.http.get<Page<UserDto>>(`${API}/api/user/${page}/${size}`);
  }


}
