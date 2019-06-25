import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';
import { UserService } from 'src/app/core/user/user.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { DepositForm } from '../../core/model/DepositForm';
import { Router } from '@angular/router';
import { Account } from '../../core/model/Account';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  depositForm: FormGroup;
  withdrawForm: FormGroup;
  account: Account;
  accountId: number;
  deposit: boolean = false;
  withdraw: boolean = false;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.accountService.getAccount(this.userService.getUserName())
      .subscribe(account => this.account = account);

    this.depositForm = this.formBuilder.group({
      value: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.withdrawForm = this.formBuilder.group({
      value: ['', Validators.required],
      password: ['', Validators.required]
    });
  }


  depositRegister() {
    if (this.depositForm.valid && !this.depositForm.pending) {
      let deposit = this.depositForm.getRawValue() as DepositForm;
      deposit.bankName = this.account.bank.name;
      deposit.accountNumber = this.account.number;

      this.accountService
        .registerDeposit(deposit)
        .subscribe(
          () => {
            this.router.navigate(['user']);
          },
          err => {
            console.log(err);
          }
        );
    }
  }

  withdrawRegister() {
    if (this.depositForm.valid && !this.depositForm.pending) {
      let deposit = this.depositForm.getRawValue() as DepositForm;
      deposit.bankName = this.account.bank.name;
      deposit.accountNumber = this.account.number;

      this.accountService
        .registerWithdraw(deposit)
        .subscribe(
          () => {
            this.router.navigate(['user']);
          },
          err => {
            console.log(err);
          }
        );
    }
  }

  depositShow() {
    this.deposit = !this.deposit;
  }

  withdrawShow() {
    this.withdraw = !this.withdraw;
  }


}
