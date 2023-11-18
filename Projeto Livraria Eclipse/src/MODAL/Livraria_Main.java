package MODAL;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Livraria_Main {

	public static void main(String[] args) {

		// Variaveis
		boolean continuaMenu = true;
		String msgLivroPesquisado = "";
		int escolhaMenu;
		String	[] nomeLivro 		= new String[8000];
		int		[] quantidadeLivro 	= new int	[8000];
		double	[] precoLivro 		= new double[8000];
		int 	   codigoLivroAtual = 0;
		int 	   cdLivroPesquisado;
		String 	nome = "";
		String 	preco = "";
		String 	qtdLivros = "";


		String[] opcoesMenu = { "Inserir Livro", "Pesquisar Livro", "Ver todos os Livros", "Finalizar Sistema" };
		String[] opcoesPesquisa = { "Alterar Livro", "Deletar Livro", "Voltar ao menu" };

		Scanner teclado = new Scanner(System.in);


		while (continuaMenu == true) {

			escolhaMenu =  JOptionPane.showOptionDialog(null, "MENU PRINCIPAL","SISTEMA GERENCIADOR DE ESTOQUE" , 
					0, 3, null, opcoesMenu, opcoesMenu[0]);
			
			//WILL : TRATATIVA, CASO O USUARIO SELECIONE O "X" DO JOPTION NO MENU, A VARIAVEL escolhaMenu IRA RECEBER "3", QUE IRA FINALIZAR O SISTEMA
			if(escolhaMenu == JOptionPane.CLOSED_OPTION) {
				escolhaMenu = 3;
			}

			switch (escolhaMenu) {
			case 0:				
				nomeLivro[codigoLivroAtual] = JOptionPane.showInputDialog
						(null, "Insira o nome do livro: ", "NOME", JOptionPane.INFORMATION_MESSAGE);

				quantidadeLivro[codigoLivroAtual] = Integer.parseInt(JOptionPane.showInputDialog
						(null, "Insira a quantidade de livros: ", "QUANTIDADE", JOptionPane.INFORMATION_MESSAGE));

				precoLivro[codigoLivroAtual] = Double.parseDouble(JOptionPane.showInputDialog
						(null, "Insira o valor do livro: ", "VALOR", JOptionPane.INFORMATION_MESSAGE));
				
				
				FormataMensagemJOPTION("O livro: " + nomeLivro[codigoLivroAtual] + "\nFoi armazenado com sucesso!", "SAINDO", "INFORMATION");
				codigoLivroAtual = codigoLivroAtual + 1;
				break;

			case 1:
				cdLivroPesquisado = Integer.parseInt(JOptionPane.showInputDialog
						(null, "Insira a codigo do livro que deseja pesquisar: ", "PESQUISA", JOptionPane.INFORMATION_MESSAGE));
				if (cdLivroPesquisado >= codigoLivroAtual || nomeLivro[cdLivroPesquisado] == "N/A") {
					FormataMensagemJOPTION("Livro não Encontrado!", "ATENÇÃO", "WARNING");
				} else {
					msgLivroPesquisado = "Pesquisa Realizada:\nNome do Livro: " + nomeLivro[cdLivroPesquisado];
					msgLivroPesquisado += "\nQuantidade do Livro: " 			+ quantidadeLivro[cdLivroPesquisado];
					msgLivroPesquisado += "\nPreço do Livro: " 					+ precoLivro[cdLivroPesquisado];
					
					escolhaMenu =  JOptionPane.showOptionDialog(null, msgLivroPesquisado,"RESULTADO" , 
							0, 3, null, opcoesPesquisa, opcoesPesquisa[0]);
					
				

					if (escolhaMenu == 0) {
						System.out.println("Insira o nome do livro: ");
						nomeLivro[cdLivroPesquisado] = teclado.next();
						System.out.println("LIVRO ALTERADO COM SUCESSO!!!");

					} else if (escolhaMenu == 1) {
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

			case 2:
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

			case 3:
				FormataMensagemJOPTION("Sistema finalizado", "SAINDO", "INFORMATION");
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

	public static void FormataMensagemJOPTION(String msg, String titulo, String tipoMsg) {

		if(tipoMsg.equals("INFORMATION")) {
			JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
		}else if(tipoMsg.equals("WARNING")) {
			JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
		}else if(tipoMsg.equals("ERROR")) {
			JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
		}


	}



}

