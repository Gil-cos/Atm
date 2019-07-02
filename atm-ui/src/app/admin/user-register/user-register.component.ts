import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewUser } from 'src/app/core/model/NewUser';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private adminService: AdminService
  ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  } 

  register() {
    if (this.registerForm.valid && !this.registerForm.pending) {
      const user = this.registerForm.getRawValue() as NewUser;
      this.adminService
        .registerUser(user)
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
