
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUser } from 'src/app/core/model/NewUser';
import { environment } from 'src/environments/environment';
import { AccountForm } from 'src/app/core/model/AccountForm';
import { UserResponse } from 'src/app/core/model/UserResponse';

const API = environment.ApiUrl;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserRegisterService {

  constructor(
    private http: HttpClient
  ) { }

  registerUser(user: NewUser) {
    let body = JSON.stringify(user);
    return this.http.post(`${API}/api/user`, body, httpOptions);
  }

  registerAccount(account: AccountForm, userId: number) {
    let body = JSON.stringify(account);
    return this.http.post(`${API}/api/account/${userId}`, body, httpOptions);
  }

  getUsers() {
    return this.http.get<UserResponse[]>(`${API}/api/user`);
  }


}
