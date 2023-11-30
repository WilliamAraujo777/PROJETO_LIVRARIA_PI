package MODAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;
 
public class Login {

	int id;
	String usuario;
	String nome;
	String senha;
	
	int nMenu = 0;

	static String[] usuarios = new String[8000];
	static String[] senhas = new String[8000];
	int posicaoUsuario;
	
	String[] opcoesMenu = { "Login", "Cadastrar", "Sair" };
	
	String menu = "\n==============================! MENU PRINCIPAL !===============================";
	boolean loop = true;

	public int Logar() {
		
		DownloadTXT("TXTs/Usuarios.txt");
		
		int opcao = 0;

		while (loop) {
			nMenu = (JOptionPane.showOptionDialog(null,menu,null, 0, 3,
					null, opcoesMenu, opcoesMenu[0]));
			
			if(nMenu == JOptionPane.CLOSED_OPTION) {
				nMenu = 2;
			}

			switch (nMenu) {
			case 0:
				loop = login();
				break;

			case 1:
				this.posicaoUsuario = cadastrar(posicaoUsuario);
				break;

			case 2:
				sair();
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
				break;
			}
		}

		return this.posicaoUsuario;
	}

	public boolean login() {
		String usuario = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
		
		
		//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
		if(usuario == null) {
			return true;
		}else if(usuario.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor, insira um texto no nome do usuario", "Erro", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		String senha = JOptionPane.showInputDialog(null, "Digite a senha do usuário:");
		if(senha == null) {
			return true;
		}else if(senha.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor, insira um texto na senha", "Erro", JOptionPane.ERROR_MESSAGE);
			return true;
		}		
		
		int indexUsuario = Arrays.asList(usuarios).indexOf(usuario);
		
		if (indexUsuario != -1 && senhas[indexUsuario].equals(senha)) {
			JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
			JOptionPane.showMessageDialog(null, "Seja bem vindo " + usuario);
			return false; // Retorna false para finalizar o loop
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
			return true;
		}

	}

	public int cadastrar(int posicaoUsuario) {
		String usuario = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
		
		
		//VERIFICO SE O BOTÃO "CANCEL" FOI CLICADO
		if(usuario == null) {
			return posicaoUsuario;
		}else if(usuario.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor, insira um texto no nome do usuario", "Erro", JOptionPane.ERROR_MESSAGE);
			return posicaoUsuario;
		}
		
		String senha = JOptionPane.showInputDialog(null, "Digite a senha do usuário:");
		if(senha == null) {
			return posicaoUsuario;
		}else if(senha.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor, insira um texto na senha", "Erro", JOptionPane.ERROR_MESSAGE);
			return posicaoUsuario;
		}		

		usuarios[posicaoUsuario] = usuario;
		senhas[posicaoUsuario] = senha;
		JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
		
		UploadTXT("TXTs/Usuarios.txt");
		
		return posicaoUsuario++;
	}

	public void sair() {
		JOptionPane.showMessageDialog(null, "Saindo...");
		System.exit(0);
	}
	
	public static void UploadTXT(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Iterar sobre as posições dos arrays
            for (int i = 0; i < usuarios.length; i++) {
            	
            	if (usuarios[i] != null) {
            		//Populando linhas para guarda-las no arquivo
	                String linha = usuarios[i] + ";" + senhas[i];
	
	                // Escrever a linha no arquivo
	                writer.write(linha);
	
	                // Adicionar quebra de linha se não for a última linha
	                if (i < usuarios.length - 1) {
	                    writer.newLine();
	                }
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void DownloadTXT(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                
                if (partes.length >= 2) {
                    usuarios[this.posicaoUsuario] = partes[0];
                    senhas[this.posicaoUsuario] = partes[1];
                    this.posicaoUsuario++;
                }
            }
        } catch (IOException | NumberFormatException e) {
        	JOptionPane.showMessageDialog(null, "Arquivo de download de dados não encontrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }
}