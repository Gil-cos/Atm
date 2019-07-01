import { Injectable } from '@angular/core';
import * as jtw_decode from 'jwt-decode';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/core/model/User';
import { TokenService } from '../token/token.service';

@Injectable({ providedIn: 'root' })
export class UserService {
    
    private userSubject = new BehaviorSubject<User>(null);
    private userName: string;
    
    constructor(private tokenService: TokenService) { 
        
        this.tokenService.hasToken() && 
        this.decodeAndNotify();
    }
    
    setToken(token: string, type: string) {
        this.tokenService.setToken(token, type);
        this.decodeAndNotify();
    }
    
    getUser() {
        return this.userSubject.asObservable();
    }
    
    private decodeAndNotify() {
        const token = this.tokenService.getToken();
        const user = jtw_decode(token) as User;
        this.userName = user.name;
        this.userSubject.next(user);
    }
    
    logout() {
        this.tokenService.removeToken();
        this.userSubject.next(null);
    }
    
    isLogged() {
        return this.tokenService.hasToken();
    }
    
    getUserName() {
        return this.userName;
    }
}