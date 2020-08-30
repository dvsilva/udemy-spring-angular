import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

import { ServicoPrestado } from './servico-prestado/servicoPrestado';
import { ServicoPrestadoBusca } from './servico-prestado/servico-prestado-lista/servicoPrestadoBusca';

@Injectable({
  providedIn: 'root',
})
export class ServicoPrestadoService {
  apiURL: string = environment.apiURLBase + '/api/servicos-prestados';

  constructor(private http: HttpClient) {}

  salvar(servicoPrestado: ServicoPrestado): Observable<ServicoPrestado> {
    return this.http.post<ServicoPrestado>(this.apiURL, servicoPrestado);
  }

  buscar(nome: string, mes: number): Observable<ServicoPrestadoBusca[]> {
    const httpParams = new HttpParams()
      .set('nome', nome)
      .set('mes', mes ? mes.toString() : '');

    // /api/servicos-prestados?nome=Maria&mes=1
    const url = this.apiURL + '?' + httpParams.toString();
    // console.log(url);
    return this.http.get<ServicoPrestadoBusca[]>(url);
  }

  /**
  atualizar(servicoPrestado: ServicoPrestado): Observable<any> {
    return this.http.put<ServicoPrestado>(`${this.apiURL}/${servicoPrestado.id}`, servicoPrestado);
  }

  getClienteById(id: number): Observable<ServicoPrestado> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  deletar(servicoPrestado: ServicoPrestado): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${servicoPrestado.id}`);
  }
   */
}
