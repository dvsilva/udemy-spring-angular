import { Component, OnInit } from '@angular/core';
import { ContatoService } from '../contato.service';
import { Contato } from './contato';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialog } from '@angular/material/dialog';
import { ContatoDetalheComponent } from '../contato-detalhe/contato-detalhe.component';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css'],
})
export class ContatoComponent implements OnInit {
  formulario: FormGroup;
  contatos: Contato[] = [];
  colunas = ['foto', 'id', 'nome', 'email', 'favorito'];
  totalElementos = 0;
  pagina = 0;
  tamanho = 10;
  pageSizeOptions: number[] = [5, 10, 15, 100];

  constructor(
    private service: ContatoService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.montarFormulario();
    this.listarContatos(this.pagina, this.tamanho);
  }

  montarFormulario() {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  listarContatos(pagina = 0, tamanho = 10) {
    this.service.list(pagina, tamanho).subscribe((response) => {
      this.contatos = response.content;
      this.totalElementos = response.totalElements;
      this.pagina = response.number;
    });
  }

  favoritar(contato: Contato) {
    this.service.favourite(contato).subscribe((resposta) => {
      contato.favorito = !contato.favorito;
    });
  }

  submit() {
    const formValues = this.formulario.value;
    const contato: Contato = new Contato(formValues.nome, formValues.email);

    this.service.save(contato).subscribe((resposta) => {
      // console.log(resposta);
      this.listarContatos();
      this.snackBar.open('O contato foi adicionado!', 'Sucesso!', {
        duration: 2000,
      });
      this.formulario.reset();
      // let lista: Contato[] = [...this.contatos, resposta];
      // this.contatos = lista;
      // this.contatos.push(resposta); // nao atualiza a datatable pois a lista eh imutavel
      // console.log(this.contatos);
    });

    /**
    const erroNomeRequired = this.formulario.controls.nome.errors.required;
    const erroEmailInvalido = this.formulario.controls.email.errors.email;
    console.log('erroNomeRequired', erroNomeRequired);
    console.log('erroEmailInvalido', erroEmailInvalido);
    console.log(this.formulario.value);
    console.log('is valid', this.formulario.valid);
    */
  }

  uploadFoto(event, contato) {
    const files = event.target.files;
    if (files) {
      const foto = files[0];
      const formData: FormData = new FormData();
      formData.append('foto', foto);

      this.service
        .upload(contato, formData)
        .subscribe((response) => this.listarContatos());
    }
  }

  visualizarContato(contato: Contato) {
    this.dialog.open(ContatoDetalheComponent, {
      width: '400px',
      height: '450px',
      data: contato,
    });
  }

  paginar(event: PageEvent) {
    this.pagina = event.pageIndex;
    this.listarContatos(this.pagina, this.tamanho);
  }

  /**
    const c: Contato = new Contato();
    c.nome = 'José';
    c.email = 'jose@email.com';
    c.favorito = true;

    this.service.save(c).subscribe((resposta) => {
      console.log(resposta);
    });
     */
}
