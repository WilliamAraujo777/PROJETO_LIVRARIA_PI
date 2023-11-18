package MODAL;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Livraria_Main {

	public static void main(String[] args) {

		//VARIAVEIS 
		boolean continuaMenu 		= true;
		String  msgLivroPesquisado 	= "";
		int 	escolhaMenu=0,codigoLivroAtual=0,cdLivroPesquisado = 0;
	
		
		//VETORES
		String	[] nomeLivro 		= new String[8000];
		String	[] opcoesMenu = {"Inserir Livro", "Pesquisar Livro", "Ver todos os Livros", "Finalizar Sistema"};
		String	[] opcoesPesquisa = {"Alterar Livro", "Deletar Livro", "Voltar ao menu" };
		String	[] opcoesUpdate = {"Nome","Quantidade","Preco"};
		int		[] quantidadeLivro 	= new int	[8000];
		double	[] precoLivro 		= new double[8000];

		
		//OBJETOS
		DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO LIVRO");
        modelo.addColumn("NOME DO LIVRO");
        modelo.addColumn("PREÇO");
        modelo.addColumn("QUANTIDADE");
        
        JTable tabelaLivros;
        JScrollPane ModeloFinalLivros;
        
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
				}else{
					
					msgLivroPesquisado = "===| Pesquisa Realizada |===\n\n\n"; 	
					msgLivroPesquisado += "Nome do Livro  : "	+ nomeLivro[cdLivroPesquisado];;
					msgLivroPesquisado += "\nQuantidade do Livro: "	+ quantidadeLivro[cdLivroPesquisado];
					msgLivroPesquisado += "\nPreço do Livro		: " + precoLivro[cdLivroPesquisado] + "\n\n\n";
					
					escolhaMenu =  JOptionPane.showOptionDialog(null, msgLivroPesquisado,"RESULTADO" , 
							JOptionPane.PLAIN_MESSAGE,1, null, opcoesPesquisa, opcoesPesquisa[0]);

					if (escolhaMenu == 0) {
						escolhaMenu =  JOptionPane.showOptionDialog(null, "Qual atributo do livro deseja alterar?","RESULTADO" , 
								JOptionPane.PLAIN_MESSAGE,1, null, opcoesUpdate, opcoesUpdate[0]);
						
						switch(escolhaMenu) {
						case 0: 
							nomeLivro[cdLivroPesquisado] =
									JOptionPane.showInputDialog(null, "Insira o novo nome do livro: ",nomeLivro[cdLivroPesquisado]);
							break;
						case 1:
							quantidadeLivro[cdLivroPesquisado] = Integer.parseInt(
									JOptionPane.showInputDialog(null, "Insira a nova quantidade do livro: ",quantidadeLivro[cdLivroPesquisado]));
							break;
						case 2:
							precoLivro[cdLivroPesquisado] = Double.parseDouble(
									JOptionPane.showInputDialog(null,"Insira o novo preço do livro: ",precoLivro[cdLivroPesquisado]));							
							break;
		
						}
						FormataMensagemJOPTION("LIVRO ALTERADO", "UPDATE", "INFORMATION");

					} else if (escolhaMenu == 1) {
						if (cdLivroPesquisado == codigoLivroAtual - 1) {
							nomeLivro[cdLivroPesquisado] = "N/A";
							codigoLivroAtual = codigoLivroAtual - 1;

						} else {
							nomeLivro[cdLivroPesquisado] = "N/A";
							for (int i = 0; i <= cdLivroPesquisado - 1; i++) {
								nomeLivro[i] = nomeLivro[i + 1];
								precoLivro[i] = precoLivro[i + 1];
								quantidadeLivro[i] = quantidadeLivro[i + 1];
								codigoLivroAtual = codigoLivroAtual -1;
							}
						}
						FormataMensagemJOPTION("LIVRO DELETADO", "DELETE", "INFORMATION");
					}
				}
				break;

			case 2:
				modelo = new DefaultTableModel();
				modelo.addColumn("CÓDIGO LIVRO");
		        modelo.addColumn("NOME DO LIVRO");
		        modelo.addColumn("PREÇO");
		        modelo.addColumn("QUANTIDADE");
				
				for (int i = 0; i < codigoLivroAtual; i++) {
					if (!"N/A".equals(nomeLivro[i])) {
						modelo.addRow(new Object[]{i, nomeLivro[i], precoLivro[i], quantidadeLivro[i]});
					}
				}
				
				 //Criando uma JTABLE e mapeando ela com meu TableModel 'MODELO', assim ela assumi as colunas e seus dados obtidos pelo for
		        tabelaLivros = new JTable(modelo);

		        //Pego minha tabela mapeada, e adiciono ao componente JScrollPane , com o objetivo de adicionar uma barra de rolagem a visualização
		        //além de deixar a mesma mais "Agradavel" do que só o JTable naturalmente.
		        ModeloFinalLivros = new JScrollPane(tabelaLivros);
		        
				JOptionPane.showMessageDialog(null, ModeloFinalLivros, "Informações dos Livros", JOptionPane.PLAIN_MESSAGE);
				
				break;

			case 3:
				FormataMensagemJOPTION("Sistema finalizado", "SAINDO", "INFORMATION");
				continuaMenu = false;
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

