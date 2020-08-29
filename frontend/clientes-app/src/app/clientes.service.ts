import { Injectable } from '@angular/core';
import { Cliente } from './clientes/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  constructor(private http: HttpClient) {}

  // Observable -> esperar requisição assincrona para retornar
  salvar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(
      'http://localhost:8080/api/clientes',
      cliente
    );
  }

  atualizar(cliente: Cliente): Observable<any> {
    return this.http.put<Cliente>(
      `http://localhost:8080/api/clientes/${cliente.id}`,
      cliente
    );
  }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('http://localhost:8080/api/clientes');
  }

  getClienteById(id: number): Observable<Cliente> {
    return this.http.get<any>(`http://localhost:8080/api/clientes/${id}`);
  }

  deletar(cliente: Cliente): Observable<any> {
    return this.http.delete<any>(
      `http://localhost:8080/api/clientes/${cliente.id}`
    );
  }

  /**
  getClientes(): Cliente[] {
    let cliente: Cliente = new Cliente();
    cliente.id = 1;
    cliente.nome = 'Fulano de tal';
    cliente.dataCadastro = '18/04/2020';
    cliente.cpf = '888.888.888-88';
    return [cliente];
  }
  */
}
