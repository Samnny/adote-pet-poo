package src.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaAdocao {
    private List<Usuario> usuarios;
    private List<Animal> animais;
    private Usuario usuarioAtual;

    public SistemaAdocao() {
        this.usuarios = new ArrayList<Usuario>();
        this.animais = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void editarUsuario(int index, Usuario usuario) {
        if (index >= 0 && index < usuarios.size()) {
            usuarios.set(index, usuario);
        }
    }

    public void excluirUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void cadastrarAnimal(Animal animal) {
        animais.add(animal);
    }

    public void editarAnimal(int index, Animal animal) {
        if (index >= 0 && index < animais.size()) {
            animais.set(index, animal);
        }
    }

    public void excluirAnimal(Animal animal) {
        animais.remove(animal);
    }

    public List<Animal> listarAnimais() {
        return animais;
    }

    public void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cadastrar Conta\n2. Entrar\n3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                System.out.println("Digite nome:");
                String nome = scanner.nextLine();
                System.out.println("Digite login:");
                String login = scanner.nextLine();
                System.out.println("Digite senha:");
                String senha = scanner.nextLine();

                Usuario novoUsuario = new Adotante(nome, login, senha);
                cadastrarUsuario(novoUsuario);
                System.out.println("Conta criada com sucesso!");
            } else if (escolha == 2) {
                System.out.println("Digite login:");
                String login = scanner.nextLine();
                System.out.println("Digite senha:");
                String senha = scanner.nextLine();

                for (Usuario user : usuarios) {
                    if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
                        usuarioAtual = user;
                        menuPrincipal();
                    }
                }
                System.out.println("Login inválido.");
            } else {
                System.out.println("Saindo...");
                break;
            }
        }
        scanner.close();
    }

    private void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Adotante\n2. Guardião\n3. Sair da sessão");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                menuAdotante();
            } else if (escolha == 2) {
                menuGuardiao();
            } else {
                usuarioAtual = null;
                System.out.println("Sessão encerrada.");
                break;
            }
        }
    }

    private void menuAdotante() {
        System.out.println("Menu de Adotante");
        // Implementação das opções do Adotante
    }

    private void menuGuardiao() {
        System.out.println("Menu de Guardião");
        // Implementação das opções do Guardião
    }
}
