programa
{
	
	funcao inicio()
	{

		//CRIANDO AS VARIAVEIS 
		logico continuaMenu = verdadeiro /*VARIAVEL COM O OBJETIVO DE CONTINUAR EXECUTANDO O "ENQUANTO" QUE MOSTRA O MENU DE OPÇÕES*/
		inteiro escolhaMenu 		   /*VARIAVEL QUE IRÁ GUARDAR A OPÇÃO QUE O USUARIO DESEJA EXECUTAR NO SISTEMA*/
		cadeia nomeLivro[8001]
 		cadeia qtdLivros[8001]
		real precoLivros[8001]

		inteiro codLivroAtual = 0
		inteiro cdLivro

		
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
					leia(nomeLivro[codLivroAtual])
          
          escreva("\nInsira a quantidade dos livros: ")
					leia(qtdLivros[codLivroAtual])
        
          escreva("\nInsira o valor do livro que deseja incluir no sistema: ")
					leia(precoLivros[codLivroAtual])

					escreva("\nO livro: " + nomeLivro[codLivroAtual] + " foi armazenado com sucesso!")
					
          codLivroAtual = codLivroAtual + 1
				pare
				caso 2:
					
					escreva("\nInsira o codigo do livro que deseja pesquisar no sistema: ")
					leia(cdLivro)
					se(cdLivro >= codLivroAtual ou nomeLivro[cdLivro] == "N/A"){
						escreva("\nLivro não Encontrado!\n")
						
					}senao{
						escreva("\nPesquisa Realizada:")
						escreva("\nNome do Livro: " + nomeLivro[cdLivro] + " - ")
            escreva("Preco do Livro: " + precoLivros[cdLivro] + " - ")
            escreva("Quantidade do Livro: " + qtdLivros[cdLivro] + "\n")

            
            escreva("O que deseja fazer? \n1 - Alterar Livro \n2 - Deletar Livro \n3 - Voltar ao Menu")
            leia(escolhaMenu)

            escolha(escolhaMenu){
              caso 1:
                  escreva("Insira o novo nome do Livro: ")
                  leia(nomeLivro[cdLivro])

                  escreva("LIVRO ALTERADO COM SUCESSO!")
              pare
                  
              caso 2:
                  nomeLivro[cdLivro] = "N/A"
                  precoLivros[cdLivro] = 0.0
                  qtdLivros[cdLivro] = "N/A"
              pare

              caso 3:
                  escreva("Voltando ao menu...")
              pare

            }
            


					}

				pare
				caso 3:
					escreva("==============================LIVROS===============================\n")
					inteiro i = 0
          para(i=0; i<codLivroAtual;i++){

          se(nomeLivro[i] != "N/A"){
            escreva("CODIGO LIVRO: " + i + " - ")
						escreva("Nome do Livro: " + nomeLivro[i] + " - ")
            escreva("Preco do Livro: " + precoLivros[i] + " - ")
            escreva("Quantidade do Livro: " + qtdLivros[i] + "\n")
          }
						
					}
					escreva("==============================FIM DA LISTA===============================\n")
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
 * @POSICAO-CURSOR = 680; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */
