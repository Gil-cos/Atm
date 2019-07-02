import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';
import { UserService } from 'src/app/core/user/user.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { DepositForm } from '../../core/model/DepositForm';
import { Router } from '@angular/router';
import { AccountDto } from '../../core/model/AccountDto';
import { MatSnackBar } from '@angular/material';
import { TransactionDto } from 'src/app/core/model/TransactionDto';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  depositForm: FormGroup;
  withdrawForm: FormGroup;
  account: AccountDto;
  accountId: number;
  deposit: boolean = false;
  withdraw: boolean = false;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private _snackBar: MatSnackBar
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
      deposit.bankName = this.account.bank;
      deposit.accountNumber = this.account.number;

      this.accountService
        .registerDeposit(deposit)
        .subscribe(
          (transactionDto: TransactionDto) => {
            this.deposit = !this.deposit;
            this.depositForm.reset();
            this.openSnackBar(transactionDto.message, 'Ok');
          },
          err => {
            console.log(err);
            this.depositForm.reset();
            this.openSnackBar(err.error.message, 'Ok');
          }
        );
    }
  }

  withdrawRegister() {
    if (this.withdrawForm.valid && !this.withdrawForm.pending) {
      let deposit = this.withdrawForm.getRawValue() as DepositForm;
      deposit.bankName = this.account.bank;
      deposit.accountNumber = this.account.number;

      this.accountService
        .registerWithdraw(deposit)
        .subscribe(
          (transactionDto: TransactionDto) => {
            this.withdraw = !this.withdraw;
            this.withdrawForm.reset();
            this.openSnackBar(transactionDto.message, 'Ok');
          },
          err => {
            this.withdrawForm.reset();
            this.openSnackBar(err.error.message, 'Ok');
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

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }


}
