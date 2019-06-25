import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountForm } from 'src/app/core/model/AccountForm';
import { UserRegisterService } from '../user-register/user-register.service';

export interface Bank {
  value: string;
  viewValue: string;
}


@Component({
  selector: 'app-account-register',
  templateUrl: './account-register.component.html',
  styleUrls: ['./account-register.component.css']
})
export class AccountRegisterComponent implements OnInit {

  registerForm: FormGroup;
  userId: number;

  banks: Bank[] = [
    { value: '1', viewValue: 'Bradesco' },
    { value: '2', viewValue: 'Itau' }
  ];


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userRegisterService: UserRegisterService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.userId = this.route.snapshot.params.userId;

    this.registerForm = this.formBuilder.group({
      bankId: ['', Validators.required],
      ownerName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  register() {
    if (this.registerForm.valid && !this.registerForm.pending) {
      const account = this.registerForm.getRawValue() as AccountForm;
      account.accountNumber = 0;
      console.log(account)
      this.userRegisterService
        .registerAccount(account, this.userId)
        .subscribe(
          () => {
            this.router.navigate(['admin']);
          },
          err => console.log(err)
        );
    }
  }

  cancel() {
    this.registerForm.reset();
  }

}
