# Tuga - Linguagem de Programação em Português

O Tuga é um compilador e máquina virtual para uma linguagem de programação em português, desenvolvido como parte de um projeto para a Unidade Curricular de Compiladores.

## Descrição

Tuga é uma linguagem de programação com sintaxe em português. O projeto inclui um compilador completo que traduz o código fonte Tuga em bytecode, e uma máquina virtual que executa esse bytecode.

## Componentes do Projeto

O projeto está organizado nos seguintes componentes:

- **Tuga**: Contém os arquivos gerados pelo ANTLR para o parser e lexer da linguagem.
- **TypeChecking**: Implementação do sistema de verificação de tipos da linguagem.
- **SymbolTable**: Gerenciamento da tabela de símbolos para variáveis, funções e escopos.
- **CodeGenerator**: Geração de bytecode a partir da árvore sintática.
- **VM**: Máquina virtual para execução do bytecode gerado.

## Características da Linguagem

- **Tipos de dados**: inteiro, real, booleano e string
- **Estruturas de controle**: se/senao, enquanto
- **Funções**: Suporte a declaração e chamada de funções
- **Operações**: Aritméticas, lógicas e relacionais

## Como Usar

1. Crie um arquivo com a extensão `.tuga` contendo seu código
2. Compile e execute o arquivo usando o seguinte comando:
   ```
   java TugaCompileAndRun arquivo.tuga
   ```

## Exemplo de Código

```
funcao principal()
inicio
    escreve "Olá, Mundo!";
fim

funcao fatorial(n: inteiro): inteiro
inicio
    se (n <= 1)
        retorna 1;
    senao
        retorna n * fatorial(n - 1);
fim
```

## Estrutura do Projeto

- `src/Tuga.g4`: Gramática ANTLR da linguagem
- `src/TugaCompileAndRun.java`: Ponto de entrada principal para compilação e execução
- `src/TypeChecking/`: Verificação de tipos
- `src/CodeGenerator/`: Geração de bytecode
- `src/VM/`: Implementação da máquina virtual
- `src/SymbolTable/`: Gerenciamento de símbolos e escopos
- `src/MyErrorListener.java`: Tratamento de erros de compilação 