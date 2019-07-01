import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { UserAuthenticated } from '../core/model/UserAuthenticated';
import { AuthService } from '../core/auth/auth.service';
import { UserService } from '../core/user/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenDto } from '../core/model/TokenDto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  userAuthenticated = new UserAuthenticated('', '');

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    const userName = this.loginForm.get('userName').value;
    const password = this.loginForm.get('password').value;
    this.userAuthenticated.userName = userName;
    this.userAuthenticated.password = password;


    this.authService
      .authenticate(this.userAuthenticated)
      .subscribe(
        (tokenDto: TokenDto) => {
          this.userService.setToken(tokenDto.token, tokenDto.type);

          this.router.navigate(['user'])

        },
        err => {
          console.log(err);
          this.loginForm.reset();
          alert('Invalid user name or password');
        }
      );
  }

}
