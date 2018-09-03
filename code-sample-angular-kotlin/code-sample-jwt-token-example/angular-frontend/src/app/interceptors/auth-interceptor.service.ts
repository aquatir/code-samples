import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenService} from "../services/token.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // No need to add token to auth request
    if (req.url.endsWith("/auth")) {
        return next.handle(req);
    }

    req = req.clone({
      setHeaders: {
        Authorization: "Bearer " + this.tokenService.getCurrentAccessToken()
      }
    });

    return next.handle(req);
  }
}
