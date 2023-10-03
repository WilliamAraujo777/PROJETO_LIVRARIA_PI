programa
{
	
	funcao inicio()
	{

		//CRIANDO AS VARIAVEIS 
		logico continuaMenu = verdadeiro /*VARIAVEL COM O OBJETIVO DE CONTINUAR EXECUTANDO O "ENQUANTO" QUE MOSTRA O MENU DE OPÇÕES*/
		inteiro escolhaMenu 		   /*VARIAVEL QUE IRÁ GUARDAR A OPÇÃO QUE O USUARIO DESEJA EXECUTAR NO SISTEMA*/
		cadeia nomeLivro[8001]
		inteiro qtdLivros = 0

		
		escreva("===========================================================================\n")
		escreva("======================PROGRAMA GERENCIADOR DE ESTOQUE======================\n")
		escreva("===========================================================================\n")
		

		
		enquanto(continuaMenu == verdadeiro) {
			escreva("\n")
			escreva("==============================MENU PRINCIPAL===============================\n 1 - INSERIR LIVROS\n 2 - PESQUISAR LIVROS\n 3 - VER TODOS OS LIVROS\n 4 - FINALIZAR PROGRAMA\n")
			escreva("Insira a opção que deseja executar: ")
			escreva("\n")
			
			leia(escolhaMenu)

			escolha(escolhaMenu){
				caso 1:
					escreva("\nInsira o nome do livro que deseja incluir no sistema: ")
					leia(nomeLivro[qtdLivros])
					escreva("\nO livro: " + nomeLivro[qtdLivros] + " foi armazenado com sucesso!")
					qtdLivros = qtdLivros + 1
				pare
				caso 2:
				
				pare
				caso 3:
					escreva("==============================LIVROS===============================\n")
					para(inteiro i=0; i<qtdLivros;i++){
						escreva("\nCODIGO LIVRO: " + i)
						escreva("\nNome do Livro: " + nomeLivro[i] + "\n")
					}
				pare
				caso 4:
					escreva("Finalizando Sistema...")
					continuaMenu = falso
				pare
				caso contrario:
					escreva("\n")
					escreva("OPÇÃO INVALIDA! INSIRA APENAS NUMEROS QUE EQUIVALEM AS OPÇÕES DO MENU")
					escreva("\n")
				pare
				
			}
			
			
		}
			
		
		
		
		

	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta seção do arquivo guarda informações do Portugol Studio.
 * Você pode apagá-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 1253; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */