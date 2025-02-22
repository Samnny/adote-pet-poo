package src.models;

import src.enums.CachorroPorte;
import src.enums.GatoPelo;
import src.repositories.UsuarioRepositorio;
import src.repositories.AnimalRepositorio;
import java.util.List;
import java.util.Scanner;

public class SistemaAdocao {
    private Usuario usuarioAtual;
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
    private AnimalRepositorio animalRepositorio = new AnimalRepositorio();
    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("Digite nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite login:");
        String login = scanner.nextLine();
        System.out.println("Digite senha:");
        String senha = scanner.nextLine();

        System.out.println("Digite email:");
        String email = scanner.nextLine();
        System.out.println("Digite telefone:");
        String telefone = scanner.nextLine();
        System.out.println("Preferência de contato (1. Ligação 2. WhatsApp 3. Ambos):");
        int preferenciaContato = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite endereço:");
        String endereco = scanner.nextLine();

        Usuario novoUsuario = new Usuario();
        usuarioRepositorio.cadastrarUsuario(novoUsuario);
        System.out.println("Conta criada com sucesso!");
    }

    private void realizarLogin() {
        System.out.println("Digite login:");
        String login = scanner.nextLine();
        System.out.println("Digite senha:");
        String senha = scanner.nextLine();

        usuarioAtual = usuarioRepositorio.buscarUsuario(login, senha);
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
        while (true) {
            System.out.println("1. Buscar Pets para Adoção\n2. Acompanhar Adoções\n3. Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                buscarPets();
            } else if (escolha == 2) {
                acompanharAdocoes();
            } else {
                break;
            }
        }
    }

    private void buscarPets() {
        List<Animal> animais = animalRepositorio.listarAnimais();
        for (Animal animal : animais) {
            System.out.println(animal);
        }
    }

    private void acompanharAdocoes() {
        System.out.println("Acompanhando processos de adoção...");
    }

    private void menuGuardiao() {
        while (true) {
            System.out.println("1. Adicionar Pet\n2. Listar Filas de Interesse\n3. Listar Meus Pets\n4. Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                adicionarPet();
            } else if (escolha == 2) {
                listarFilasDeInteresse();
            } else if (escolha == 3) {
                listarMeusPets();
            } else {
                break;
            }
        }
    }

    private void adicionarPet() {
        System.out.println("Digite nome do pet:");
        String nome = scanner.nextLine();

        System.out.println("Digite tipo do pet (Cachorro/Gato):");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.println("Digite cor do pet:");
        String cor = scanner.nextLine();

        System.out.println("Digite raça do pet:");
        String raca = scanner.nextLine();

        Animal novoAnimal;
        if (tipo.equals("cachorro")) {
            System.out.println("Digite porte do cachorro (PEQUENO, MEDIO, GRANDE):");
            CachorroPorte porte = CachorroPorte.valueOf(scanner.nextLine().toUpperCase());
            novoAnimal = new Cachorro(animalRepositorio.gerarId(), nome, cor, raca, porte);
        } else if (tipo.equals("gato")) {
            System.out.println("Digite tipo de pelo do gato (CURTO, MEDIO, LONGO):");
            GatoPelo pelo = GatoPelo.valueOf(scanner.nextLine().toUpperCase());
            novoAnimal = new Gato(animalRepositorio.gerarId(), nome, cor, raca, pelo);
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        animalRepositorio.cadastrarAnimal(novoAnimal);
        System.out.println("Pet adicionado com sucesso!");
    }

    private void listarFilasDeInteresse() {
        System.out.println("Listando filas de interesse...");
        // Implementar lógica de exibição das filas
    }

    private void listarMeusPets() {
        System.out.println("Listando pets do guardiao...");
    }
}
