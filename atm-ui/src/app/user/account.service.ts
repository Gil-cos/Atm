import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DepositForm } from '../core/model/DepositForm';
import { AccountDto } from '../core/model/AccountDto';

const API = environment.ApiUrl;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AccountService {

  constructor(
    private http: HttpClient
  ) { }

  getAccount(name: string) {
    return this.http.get<AccountDto>(`${API}/api/account/${name}`);
  }

  registerDeposit(deposit: DepositForm) {
    let body = JSON.stringify(deposit);
    return this.http.post(`${API}/api/atm/deposit`, body, httpOptions);
  }

  registerWithdraw(deposit: DepositForm) {
    let body = JSON.stringify(deposit);
    return this.http.post(`${API}/api/atm/withdraw`, body, httpOptions);
  }

}
