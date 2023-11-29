 package MODAL;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Login {

    int id;
    String usuario;
    String nome;
    String senha;

    String[] usuarios = new String[8000];
    String[] senhas = new String[8000];

    String menu = "\n==============================! MENU PRINCIPAL !===============================\n 1 - LOGIN\n 2 - CADASTRAR\n 3 - SAIR";
    boolean loop = true;

    public int Logar(int posicaoUsuario) {
        int opcao = 0;

        while (loop) {
            String nMenu = JOptionPane.showInputDialog(null, menu);
            opcao = nMenu.charAt(0);

            switch (opcao) {
                case '1':
                    loop = login();
                    break;

                case '2':
                    posicaoUsuario = cadastrar(posicaoUsuario);
                    break;

                case '3':
                    sair();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        }

        return posicaoUsuario;
    }

    public boolean login() {
        String usuario = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
        String senha = JOptionPane.showInputDialog(null, "Digite a senha do usuário:");

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
        String senha = JOptionPane.showInputDialog(null, "Digite a senha do usuário:");

        usuarios[posicaoUsuario] = usuario;
        senhas[posicaoUsuario] = senha;
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        return posicaoUsuario++;
    }

    public void sair() {
        JOptionPane.showMessageDialog(null, "Saindo...");
        System.exit(0);
    }
}