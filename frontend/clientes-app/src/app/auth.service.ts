import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

import { Usuario } from './login/usuario';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiURL: string = environment.apiURLBase + '/api/usuarios';
  tokenUrl: string = environment.apiURLBase + environment.obterTokenUrl;
  clientId: string = environment.clientId;
  clientSecret: string = environment.clientSecret;
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {}

  obterToken() {
    const tokenString = localStorage.getItem('access_token');
    if (tokenString) {
      const token = JSON.parse(tokenString);
      return token.access_token;
    }
    return null;
  }

  encerrarSessao() {
    localStorage.removeItem('access_token');
  }

  getUsuarioAutenticado() {
    const token = this.obterToken();

    if (token) {
      const usuario = this.jwtHelper.decodeToken(token).user_name;
      return usuario;
    }

    return null;
  }

  isAutenticated(): boolean {
    const token = this.obterToken();

    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }

    return false;
  }

  salvar(usuario: Usuario): Observable<any> {
    return this.http.post<Usuario>(this.apiURL, usuario);
  }

  tentarLogar(username: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');

    // btoa -> realiza transformacao ta string em hexadecimal
    const headers = {
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded',
    };

    return this.http.post(this.tokenUrl, params.toString(), { headers });
  }
}
