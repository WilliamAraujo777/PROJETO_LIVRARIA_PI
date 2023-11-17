package MODAL;

import java.util.Scanner;

public class Livraria_Main {

	public static void main(String[] args) {

		// Variaveis
		boolean continuaMenu = true;
		int escolhaMenu;
		String	[] nomeLivro 		= new String[8000];
		int		[] quantidadeLivro 	= new int	[8000];
		double	[] precoLivro 		= new double[8000];
		int 	   codigoLivroAtual = 0;
		int 	   cdLivroPesquisado;

		System.out.println("===========================================================================");
		System.out.println("======================PROGRAMA GERENCIADOR DE ESTOQUE======================");

		Scanner teclado = new Scanner(System.in);

		while (continuaMenu == true) {
			System.out.println(
					"==============================MENU PRINCIPAL===============================\n 1 - INSERIR LIVROS\n 2 - PESQUISAR LIVROS\n 3 - VER TODOS OS LIVROS\n 4 - FINALIZAR PROGRAMA\n");

			System.out.println("Insira a opção que deseja executar: ");
			escolhaMenu = teclado.nextInt();

			switch (escolhaMenu) {
			case 1:
				System.out.println("\nInsira o nome do livro que deseja incluir no sistema: ");
				nomeLivro[codigoLivroAtual] = teclado.next();
				System.out.println("\nInsira a quantidade dos livros: ");
				quantidadeLivro[codigoLivroAtual] = teclado.nextInt();
				System.out.println("\nInsira o valor do livro que deseja incluir no sistema: ");
				precoLivro[codigoLivroAtual] = teclado.nextDouble();
				System.out.println("\nO livro: " + nomeLivro[codigoLivroAtual] + " foi armazenado com sucesso!");
				codigoLivroAtual = codigoLivroAtual + 1;
				break;

			case 2:
				System.out.println("\nInsira o codigo do livro que deseja pesquisar no sistema: ");
				cdLivroPesquisado = teclado.nextInt();
				if (cdLivroPesquisado >= codigoLivroAtual || nomeLivro[cdLivroPesquisado] == "N/A") {
					System.out.println("\nLivro não Encontrado!\n");
				} else {
					System.out.println("\nPesquisa Realizada:");
					System.out.println("\nNome do Livro: " + nomeLivro[cdLivroPesquisado] + " - ");
					System.out.println("Quantidade do Livro: " + quantidadeLivro[cdLivroPesquisado] + "\n");
					System.out.println(
							"O que deseja fazer? \n1 - Alterar Livro \n2 - Deletar Livro \n3 - Voltar ao Menu");
					escolhaMenu = teclado.nextInt();

					if (escolhaMenu == 1) {
						System.out.println("Insira o nome do livro: ");
						nomeLivro[cdLivroPesquisado] = teclado.next();
						System.out.println("LIVRO ALTERADO COM SUCESSO!!!");

					} else if (escolhaMenu == 2) {
						if (cdLivroPesquisado == codigoLivroAtual - 1) {
							nomeLivro[cdLivroPesquisado] = "N/A";
							cdLivroPesquisado = cdLivroPesquisado - 1;

						} else {
							nomeLivro[cdLivroPesquisado] = "N/A";
							for (int i = 0; i <= cdLivroPesquisado - 1; i++) {
								nomeLivro[i] = nomeLivro[i + 1];
								precoLivro[i] = precoLivro[i + 1];
								quantidadeLivro[i] = quantidadeLivro[i + 1];
								cdLivroPesquisado = cdLivroPesquisado -1;
							}
						}
						System.out.println("LIVRO DELETADO COM SUCESSO!!!");
					}
				}
				break;

			case 3:
				System.out.println("==============================INICIO DA LISTA===============================\n");
				for (int i = 0; i < codigoLivroAtual; i++) {
					if (nomeLivro[i] != "N/A") {
						System.out.print("CODIGO LIVRO: " + i + " - ");
						System.out.print("Nome do Livro: " + nomeLivro[i] + " - ");
						System.out.print("Preco do Livro: " + precoLivro[i] + " - ");
						System.out.print("Quantidade do Livro: " + quantidadeLivro[i] + "\n");
					}
				}
				System.out.println("\n==============================FIM DA LISTA===============================");
				break;

			case 4:
				System.out.println("Finalizando Sistema...");
				continuaMenu = false;
				break;

			default:
				System.out.println("\n");
				System.out.println("OPÇÃO INVALIDA! INSIRA APENAS NUMEROS QUE EQUIVALEM AS OPÇÕES DO MENU");
				System.out.println("\n");
				break;
			}
		}
	}
}

