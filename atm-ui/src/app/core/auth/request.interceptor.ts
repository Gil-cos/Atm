import { Injectable } from "@angular/core";
import { HttpInterceptor } from "@angular/common/http";
import { HttpRequest } from "@angular/common/http";
import { HttpHandler } from "@angular/common/http";
import { Observable } from "rxjs";
import { HttpSentEvent } from "@angular/common/http";
import { HttpHeaderResponse } from "@angular/common/http";
import { HttpProgressEvent } from "@angular/common/http";
import { HttpResponse } from "@angular/common/http";
import { HttpUserEvent } from "@angular/common/http";
import { TokenService } from "../token/token.service";
import { tap } from 'rxjs/operators';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

    constructor(private tokenService: TokenService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpSentEvent
        | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        console.log('sqsqss')
        if (this.tokenService.hasToken()) {
            const token = this.tokenService.getToken();
            req = req.clone({
                setHeaders: {
                    'Authorization': token
                }
            });
        }
        return next.handle(req)
            .pipe(
                tap(event => {
                    if (event instanceof HttpResponse) {

                        console.log(" all looks good");
                        // http response status code
                        console.log(event.status);
                    }
                }, error => {
                    // http response status code
                    console.log("----response----");
                    console.error("status code:");
                    console.error(error.status);
                    console.error(error.message);
                    console.log("--- end of response---");

                })
            );
    }
}