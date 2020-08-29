import { Component } from '@angular/core';
import { ClientesComponent } from '../clientes/clientes.component';

@Component({
  selector: 'hello',
  templateUrl: './hello.component.html',
})
export class HelloComponent {
  nome: string; // any recebe qualquer coisa
  clientes: Cliente[];

  constructor() {
    this.nome = 'Danyllo';

    let fulano = new Cliente('Fulano', 30);
    let cicrano = new Cliente('Cicrano', 25);
    let outro = new Cliente('Outro', 23);

    this.clientes = [fulano, cicrano, outro];
  }
}

class Cliente {
  constructor(public nome: string, public idade: number) {}
}
