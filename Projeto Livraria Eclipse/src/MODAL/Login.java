package MODAL;

import java.util.Scanner;

import java.util.Arrays;

public class Login {
	int id;
	String usuario;
	String nome;
	String senha;

	String[] usuarios = new String[8000];
	String[] senhas = new String[8000];

	Scanner teclado = new Scanner(System.in);

	public int Logar(int posicaoUsuario) {

		boolean loop = true;
		int opcao = 0;

		while (loop) {

			System.out.println(
					"\n==============================! MENU PRINCIPAL !===============================\n 1 - LOGIN\n 2 - CADASTRAR\n 3 - SAIR");

			System.out.printf(" ESCOLHA: ");
			try {
				opcao = teclado.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Erro: Opção inválida. Digite um número inteiro.");
				teclado.next(); // Limpar o buffer do scanner

				continue; // Volta ao início do loop
			}

			switch (opcao) {
			// Verificação se o usuario existe dentro do array USUARIOS
			case 1:

				login(loop);

				break;

			case 2:
				posicaoUsuario = cadastrar(posicaoUsuario);
				break;

			case 3:
				// Saida do loop
				sair();
				break;

			default:
				// Algum dado invalido foi inserido, então retorno esse erro
				System.out.print("opcao invalida");

				break;
			}

		}

		return posicaoUsuario;
	}

	public boolean login(boolean loop) {
		System.out.println("Digite o nome do usuario:");

		this.usuario = teclado.next();

		System.out.println("Digite a senha do usuario");

		this.senha = teclado.next();

		// Variavel responsavel por fazer a verificação se o LOGINUSUARIO existe dentro
		// do array USUARIOS
		int indexUsuario = Arrays.asList(usuarios).indexOf(this.usuario);

		// Se o usuario existir dentro do array usuarios e a senha digitada (passo como
		// parametro do array senhas o indexUsuario para poder vincular o usuario a
		// senha do mesmo)
		// existir dentro do array loginSenha o sistema efetuara o login do usuario,
		// caso contrario retornara um erro
		if (indexUsuario != -1 && senhas[indexUsuario].equals(this.senha)) {
			System.out.println("Login bem-sucedido!");
			loop = false;
		} else {
			System.out.println("Usuário ou senha incorretos");
		}
		return loop;
	}

	public int cadastrar(int posicaoUsuario) {
		// Inserção dos dados senha e usuario nos seus respectivos arrays

		System.out.print("\n==========! Cadastro de usuario !==========");
		
		System.out.print("\nDigite o nome do usuario: ");
		this.usuario = teclado.next();
		
		System.out.print("\nDigite a senha do usuario: ");
		this.senha = teclado.next();

		// a variavel posicaoUsuario tem a funcao de controlar a posicao dos dados
		// inseridos, toda vez que eu inserir uma senha ou usuario,
		// ela recebera ela mesma + 1, dessa maneira os dados não se misturam e cada um
		// recebe uma posicao
		usuarios[posicaoUsuario] = this.usuario;
		senhas[posicaoUsuario] = this.senha;
		System.out.println("Usuario Cadastrado com sucesso!!!");

		return posicaoUsuario++;
	}

	public void sair() {
		System.out.println("saindo...");
		System.exit(0);
	}

}