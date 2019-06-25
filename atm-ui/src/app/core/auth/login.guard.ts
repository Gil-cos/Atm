import { Injectable } from '@angular/core';
import { UserService } from '../user/user.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root'})
export class LoginGuard implements CanActivate {

    constructor(
        private userService: UserService,
        private router: Router) {}

    canActivate(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
            
            if(this.userService.isLogged()){
                if (this.userService.getUserName() == 'admin') {
                    this.router.navigate(['admin'])
                } else {
                    this.router.navigate(['user'])
                }
                return false;
            }
            return true;
    }
}