package MODAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Livraria_Main {
	static String	[] nomeLivro 		= new String[8000];
	static int		[] quantidadeLivro 	= new int	[8000];
	static int		codigoLivroAtual 	= 0;
	static double	[] precoLivro 		= new double[8000];
	
	public static void main(String[] args) {
		
		
		
		//VARIAVEIS 
		boolean continuaMenu 		= true;
		boolean continuaLivro = true;
		String  msgLivroPesquisado 	= "";
		String 	novoNomeLivro = "";
		String valor = "";
		int 	novaQuantidadeLivro = 0;
		int 	escolhaMenu=0,cdLivroPesquisado = 0, posicaoUsuario = 0;
		Double 	novoPrecoLivro = 0.0;
	
		//VETORES
		String	[] opcoesMenu = {"Inserir Livro", "Pesquisar Livro", "Ver todos os Livros", "Finalizar Sistema"};
		String	[] opcoesPesquisa = {"Alterar Livro", "Deletar Livro", "Voltar ao menu" };
		String	[] opcoesUpdate = {"Nome","Quantidade","Preco"};

		//OBJETOS
		Login login = new Login();
		DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO LIVRO");
        modelo.addColumn("NOME DO LIVRO");
        modelo.addColumn("PREÇO");
        modelo.addColumn("QUANTIDADE");
        JTable tabelaLivros;
        JScrollPane ModeloFinalLivros;
        
        login.Logar();
        
        //POPULO OS VETORES DE ACORDO COM O TXT
		DownloadTXT("TXTs/Livros.txt");
        
		while (continuaMenu == true) {

			escolhaMenu =  JOptionPane.showOptionDialog(null, "MENU PRINCIPAL","SISTEMA GERENCIADOR DE ESTOQUE" , 
					0, 3, null, opcoesMenu, opcoesMenu[0]);
			
			//WILL : TRATATIVA, CASO O USUARIO SELECIONE O "X" DO JOPTION NO MENU, A VARIAVEL escolhaMenu IRA RECEBER "3", QUE IRA FINALIZAR O SISTEMA
			if(escolhaMenu == JOptionPane.CLOSED_OPTION) {
				escolhaMenu = 3;
			}

			switch (escolhaMenu) {
			case 0:		
				continuaLivro = true;
				while(continuaLivro) {
					novoNomeLivro = JOptionPane.showInputDialog
							(null, "Insira o nome do livro: ", "NOME", JOptionPane.INFORMATION_MESSAGE);
					
					//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
					if(novoNomeLivro == null) {
						continuaLivro = false;
					}else if(novoNomeLivro.trim().equals("")) {
						FormataMensagemJOPTION("Por favor, insira um texto no nome do Livro", "Erro", "ERROR");
						continue;
					}else if(novoNomeLivro.length()>80) {
						FormataMensagemJOPTION("Por favor, não insira texto com mais de 50 caracteres no nome do Livro", "Erro", "ERROR");
						continue;
					}else{
						try {
				            validaNomeLivro(novoNomeLivro);
				        } catch (IllegalArgumentException e) {
				        	FormataMensagemJOPTION(e.getMessage(), "Erro", "ERROR");
				        	continue;
				        }
					}
					continuaLivro = false;
				}
				
				if(novoNomeLivro == null) {
					break;
				}
				
				//QUANTIDADE DE LIVROS
				continuaLivro = true;
				
				while(continuaLivro) {
					valor = JOptionPane.showInputDialog
							(null, "Insira a quantidade de livros: ", "QUANTIDADE", JOptionPane.INFORMATION_MESSAGE);
					
					//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
					if(valor == null) {
						continuaLivro = false;
					}else {
						//VALIDAÇÃO SE FOI INCLUIDO UM NUMERO VALIDO NA VARIAVEL DE QUANTIDADE
						try {
							novaQuantidadeLivro = Integer.parseInt(valor);	
						} catch (NumberFormatException e) {
							FormataMensagemJOPTION("Por favor, insira um número válido para a quantidade de Livros", "Erro", "ERROR");
							continue;
						}
					}
					continuaLivro = false;
				}
				if(valor == null) {
					break;
				}
				
				//VALOR DO LIVRO
				continuaLivro = true;
				
				while(continuaLivro) {
					valor = JOptionPane.showInputDialog
							(null, "Insira o valor do livro: ", "VALOR", JOptionPane.INFORMATION_MESSAGE);
					
					//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO				
					if(valor == null) {
						continuaLivro = false;
					}else {
						//VALIDAÇÃO SE FOI INCLUIDO UM NUMERO VALIDO NA VARIAVEL DE VALOR
						try {
							novoPrecoLivro = Double.parseDouble(valor);	
						} catch (NumberFormatException e) {
							FormataMensagemJOPTION("Por favor, insira um número válido para o valor dos Livros", "Erro", "ERROR");
							continue;
						}	
					}
					continuaLivro = false;
				}
				
				if(valor == null) {
					break;
				}

				
				nomeLivro[codigoLivroAtual] 		= novoNomeLivro;
				quantidadeLivro[codigoLivroAtual] 	= novaQuantidadeLivro;
				precoLivro[codigoLivroAtual] 		= novoPrecoLivro;

				FormataMensagemJOPTION("O livro: " + nomeLivro[codigoLivroAtual] + "\nFoi armazenado com sucesso!", "SAINDO", "INFORMATION");
				UploadTXT("TXTs/Livros.txt");
				codigoLivroAtual = codigoLivroAtual + 1;
				break;
			case 1:
				
				valor = JOptionPane.showInputDialog
						(null, "Insira a codigo do livro que deseja pesquisar: ", "PESQUISA", JOptionPane.INFORMATION_MESSAGE);
				
				//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO				
				if(valor == null) {
					break;
				}
				
				//VALIDAÇÃO SE FOI INCLUIDO UM NUMERO VALIDO NA VARIAVEL DE PESQUISA DO LIVRO
				try {
					cdLivroPesquisado = Integer.parseInt(valor);
				} catch (NumberFormatException e) {
					FormataMensagemJOPTION("Por favor, insira um número válido para pesquisar os livros", "Erro", "ERROR");
					break;
				}
				
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
							
							continuaLivro = true;
							while(continuaLivro) {
								novoNomeLivro = JOptionPane.showInputDialog(null, "Insira o novo nome do livro: ",nomeLivro[cdLivroPesquisado]);

								//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
								if(novoNomeLivro == null) {
									continuaLivro = false;
								}else if(novoNomeLivro.trim().equals("")) {
									FormataMensagemJOPTION("Por favor, insira um texto no nome do Livro", "Erro", "ERROR");
									continue;
								}else if(novoNomeLivro.length()>80) {
									FormataMensagemJOPTION("Por favor, não insira texto com mais de 50 caracteres no nome do Livro", "Erro", "ERROR");
									continue;
								}else{
									try {
							            validaNomeLivro(novoNomeLivro);
							        } catch (IllegalArgumentException e) {
							        	FormataMensagemJOPTION(e.getMessage(), "Erro", "ERROR");
							        	continue;
							        }
								}
								continuaLivro = false;
							}
							
							if(novoNomeLivro == null) {
								break;
							}

							nomeLivro[cdLivroPesquisado] = novoNomeLivro;
							
							FormataMensagemJOPTION("LIVRO ALTERADO", "UPDATE", "INFORMATION");
							UploadTXT("TXTs/Livros.txt");
							break;
						case 1:
							//QUANTIDADE DE LIVROS
							continuaLivro = true;
							
							while(continuaLivro) {
								valor = JOptionPane.showInputDialog(null, "Insira a nova quantidade do livro: ",quantidadeLivro[cdLivroPesquisado]);
								//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
								if(valor == null) {
									continuaLivro = false;
								}else {
									//VALIDAÇÃO SE FOI INCLUIDO UM NUMERO VALIDO NA VARIAVEL DE QUANTIDADE
									try {
										novaQuantidadeLivro = Integer.parseInt(valor);	
									} catch (NumberFormatException e) {
										FormataMensagemJOPTION("Por favor, insira um número válido para a quantidade de Livros", "Erro", "ERROR");
										continue;
									}
								}
								continuaLivro = false;
							}
							
							//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
							if(valor == null) {
								break;
							}
							
							quantidadeLivro[cdLivroPesquisado] = novaQuantidadeLivro;
							
							FormataMensagemJOPTION("LIVRO ALTERADO", "UPDATE", "INFORMATION");
							UploadTXT("TXTs/Livros.txt");
							break;
						case 2:
							//QUANTIDADE DE LIVROS
							continuaLivro = true;
							
							while(continuaLivro) {
								valor = JOptionPane.showInputDialog(null,"Insira o novo preço do livro: ",precoLivro[cdLivroPesquisado]);

								//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO				
								if(valor == null) {
									continuaLivro = false;
								}else {
									//VALIDAÇÃO SE FOI INCLUIDO UM NUMERO VALIDO NA VARIAVEL DE VALOR
									try {
										novoPrecoLivro = Double.parseDouble(valor);	
									} catch (NumberFormatException e) {
										FormataMensagemJOPTION("Por favor, insira um número válido para o valor dos Livros", "Erro", "ERROR");
										continue;
									}	
								}
								continuaLivro = false;
							}
							
							//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
							if(valor == null) {
								break;
							}
							precoLivro[cdLivroPesquisado] = novoPrecoLivro;	
							
							FormataMensagemJOPTION("LIVRO ALTERADO", "UPDATE", "INFORMATION");
							UploadTXT("TXTs/Livros.txt");
							break;
						}
					} else if (escolhaMenu == 1) {
						if (cdLivroPesquisado == codigoLivroAtual - 1) {
							nomeLivro[cdLivroPesquisado] = null;
							codigoLivroAtual = codigoLivroAtual - 1;

						} else {
							nomeLivro[cdLivroPesquisado] = null;
							for (int i = 0; i <= cdLivroPesquisado - 1; i++) {
								nomeLivro[i] = nomeLivro[i + 1];
								precoLivro[i] = precoLivro[i + 1];
								quantidadeLivro[i] = quantidadeLivro[i + 1];
								codigoLivroAtual = codigoLivroAtual -1;
							}
						}
						UploadTXT("TXTs/Livros.txt");
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
					if (nomeLivro[i] != null) {
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
	
	public static void UploadTXT(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Iterar sobre as posições dos arrays
            for (int i = 0; i < nomeLivro.length; i++) {
            	
            	if (nomeLivro[i] != null) {
            		//Populando linhas para guarda-las no arquivo
	                String linha = nomeLivro[i] + ";" + quantidadeLivro[i] + ";" + precoLivro[i];
	
	                // Escrever a linha no arquivo
	                writer.write(linha);
	
	                // Adicionar quebra de linha se não for a última linha
	                if (i < nomeLivro.length - 1) {
	                    writer.newLine();
	                }
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void DownloadTXT(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                	
                
                if (partes.length >= 3) {
                    nomeLivro[codigoLivroAtual] = partes[0];
                    quantidadeLivro[codigoLivroAtual] = Integer.parseInt(partes[1]);
                    precoLivro[codigoLivroAtual] = Double.parseDouble(partes[2]);

                    codigoLivroAtual++;
                }
            }
        } catch (IOException | NumberFormatException e) {
        	FormataMensagemJOPTION("Arquivo de download de dados não encontrado", "Atenção", "WARNING");
        }
    }
	
	  public static void validaNomeLivro(String nomeLivro) {
	        // Define uma lista de caracteres especiais que você deseja bloquear
	        char[] CaracteresInvalidos = {'!', '@', '#', '$', '%', '¨', '&', '*', '(', ')','`', '{', '^', '}', ':', '?', '>', '<', '+', '=', ';'};

	        // Verifica se a string contém algum dos caracteres especiais
	        for (char cont : CaracteresInvalidos) {
	            if (nomeLivro.indexOf(cont) != -1) {
	                throw new IllegalArgumentException("A string contém caracteres especiais.");
	            }
	        }
	    }
}

