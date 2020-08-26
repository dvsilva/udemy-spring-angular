// tipo let, var ou const 
var numero : number = 1;
var nome : string;
var flag: boolean = true;
var umArray: string[] = ['A', 'B'];

nome = 'fulano';
console.log(nome);

class Pessoa {
    constructor( 
        private nome: string,
        private idade: number) {
        
    }
    imprimirNome(){
        console.log(this.obterNome());
    }

    obterNome() : string {
        return nome;
    }
}

let pessoa : Pessoa;
pessoa = new Pessoa("Fulano", 25);
pessoa.imprimirNome();
console.log(pessoa.obterNome());