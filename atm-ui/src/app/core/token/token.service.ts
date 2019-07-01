import { Injectable } from '@angular/core';

const KEY = 'Authorization';

@Injectable({ providedIn: 'root' })
export class TokenService {

    hasToken() {
        return !!this.getToken();
    }

    setToken(token, type) {
        window.localStorage.setItem(KEY, type + " " + token);
    }

    getToken() {
        return window.localStorage.getItem(KEY);
    }

    removeToken() {
        window.localStorage.removeItem(KEY);
    }

}