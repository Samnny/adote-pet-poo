package src.models;

import src.repositories.UsuarioRepositorio;
import utils.Menu;

import java.util.Scanner;

public class SistemaAdocao {
    private Usuario usuarioAtual;
    private Scanner scanner = new Scanner(System.in);

    public static UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void iniciarSistema() {
        while (true) {
            System.out.println("1. Cadastrar Conta\n2. Entrar\n3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                cadastrarUsuario();
            } else if (escolha == 2) {
                realizarLogin();
            } else {
                System.out.println("Saindo...");
                break;
            }
        }
    }

    private void cadastrarUsuario() {
    	Email emailUsuario;
    	Telefone telefoneUsuario;
    	
        System.out.println("Digite nome:");
        String nome = scanner.nextLine();
        
        String login;
        while (true) {
        	System.out.println("Digite login:");
        	login = scanner.nextLine();
        	
        	if (usuarioRepositorio.buscarUsuario(login)) {
        		System.out.println("Esse login já existe. Tente novamente.");
        	} else {
        		break;
        	}
        }
        System.out.println("Digite senha:");
        String senha = scanner.nextLine();

        while (true) {
        	System.out.println("Digite email:");
        	String email = scanner.nextLine();
        
        	emailUsuario = new Email(email);
	        if (!emailUsuario.validar()) {
	        	System.out.println("Email no formato errado. Tente novamente.");
	        } else {
	        	break;
	        }
        }
        
        int preferenciaContato = 0;
        while (true) {
        	System.out.println("Digite telefone:");
        	String telefone = scanner.nextLine();
        	
        	boolean whatsapp = true;
        	boolean ligacao = true;
        	if (preferenciaContato == 0) {        		
        		System.out.println("Preferência de contato (1. Ligação 2. WhatsApp 3. Ambos):");
        		System.out.println("Por padrão, a opção \"ambos\" é selecionado.");
        		preferenciaContato = scanner.nextInt();
        		scanner.nextLine();
        		
        		switch(preferenciaContato) {
        		case 1:
        			whatsapp = true;
        			ligacao = false;
        			break;
        		case 2:
        			whatsapp = false;
        			ligacao = true;
        			break;
        		case 3:
        		default:
        			whatsapp = true;
        			ligacao = true;
        			break;
        		}
        	}
        	
        	telefoneUsuario = new Telefone(telefone, whatsapp, ligacao);
        	if (!telefoneUsuario.validar()) {
        		System.out.println("Telefone no formato errado. Tente novamente.");
        	} else {
        		break;
        	}
        }

        System.out.println("Digite seu bairro:");
        String bairro = scanner.nextLine();
        System.out.println("Digite sua cidade:");
        String cidade = scanner.nextLine();
        System.out.println("Digite seu estado:");
        String estado = scanner.nextLine();
        
        Endereco endereco = new Endereco(bairro, cidade, estado);

        Usuario novoUsuario = new Usuario(usuarioRepositorio.gerarId(), nome, login, senha, emailUsuario, telefoneUsuario, endereco);
        usuarioRepositorio.cadastrarUsuario(novoUsuario);
        System.out.println("Conta criada com sucesso!");
    }

    private void realizarLogin() {
        System.out.println("Digite login:");
        String login = scanner.nextLine();
        System.out.println("Digite senha:");
        String senha = scanner.nextLine();

        usuarioAtual = usuarioRepositorio.autenticarUsuario(login, senha);
        if (usuarioAtual != null) {
            System.out.println("Login bem-sucedido!");
            menuPrincipal();
        } else {
            System.out.println("Login inválido.");
        }
    }

    private void menuPrincipal() {
        while (true) {
            System.out.println("1. Adotante\n2. Guardião\n3. Sair da sessão");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
            	Adotante usuario = (Adotante) usuarioAtual;
            	Menu.MenuUsuario(usuario);
            } else if (escolha == 2) {
            	Guardiao usuario = (Guardiao) usuarioAtual;
            	Menu.MenuUsuario(usuario);
            } else {
                usuarioAtual = null;
                System.out.println("Sessão encerrada.");
                break;
            }
        }
    }
}
