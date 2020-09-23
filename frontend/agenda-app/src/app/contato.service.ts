import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Contato } from './contato/contato';
import { PaginaContato } from './contato/paginaContato';

import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ContatoService {
  url: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  save(contato: Contato): Observable<Contato> {
    return this.http.post<Contato>(this.url, contato);
  }

  list(page, size): Observable<PaginaContato> {
    console.log(page, size);
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<any>(`${this.url}?${params.toString()}`);
  }

  favourite(contato: Contato): Observable<any> {
    return this.http.patch(`${this.url}/${contato.id}/favorito`, null);
  }

  upload(contato: Contato, formData: FormData): Observable<any> {
    return this.http.put(`${this.url}/${contato.id}/foto`, formData, {
      responseType: 'blob',
    });
  }
}
