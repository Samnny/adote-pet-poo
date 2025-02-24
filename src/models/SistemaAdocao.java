package src.models;

import src.enums.CachorroPorte;
import src.enums.GatoPelo;
import src.repositories.UsuarioRepositorio;
import src.repositories.AnimalRepositorio;

import java.util.ArrayList;
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
        	if (animal.getAdotante() == null) {
        		System.out.println(animal.toString());
                System.out.println("Raça do Animal: " + animal.getRaca());
                System.out.println("Cor do Animal: " + animal.getCor());
        	}
        }
        menuBuscarPetsDisponiveis();
    }
    
    private void menuBuscarPetsDisponiveis() {
    	/* TODO: fazer menu de buscar pets disponíveis pro adotante:
    	 * Deve conter as opções
    	 * - Filtrar pets
		 * - Selecionar um pet
		 * - Voltar ao menu anterior */
    }

    private void acompanharAdocoes() {
        System.out.println("Acompanhando processos de adoção...");
        List<Animal> animais = animalRepositorio.listarAnimais();
        for (Animal animal : animais) {
        	for (FilaInteresseItem candidatura : animal.getFilaInteresse()) {
        		if (candidatura.getInteressado().getId() == usuarioAtual.getId()) {
                	System.out.println(animal.toString());
        			System.out.print("Status da Fila de Interesse: ");
        			System.out.println(animal.getStatusFilaInteresse());
        			System.out.print("Data da sua aplicação: ");
        			System.out.println(candidatura.getData());
        			System.out.print("Sua mensagem de aplicação: ");
                	System.out.println(candidatura.getMensagem());
                	System.out.println("==================================");
        			break;
        		}
        	}
        }
        menuFilaInteresseAdotante();
    }
    
    private void menuFilaInteresseAdotante() {
    	while (true) {
    		System.out.println("1. Escolher processo de adoção para cancelar\n2.Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 1) {
    			cancelarProcessoAdocao();
    		} else {
    			break;
    		}
    	}
    }
    
    private void cancelarProcessoAdocao() {
    	while (true) {
    		System.out.println("Digite o id do animal do qual deseja sair da fila de interesse:");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		Animal animal = animalRepositorio.buscarAnimal(escolha);
    		
    		if (animal != null) {
    			ArrayList<FilaInteresseItem> novaFilaInteresse = new ArrayList<FilaInteresseItem>();
    			novaFilaInteresse = animal.getFilaInteresse();
    			boolean adotanteEncontrado = false;
    			for (FilaInteresseItem candidatura : animal.getFilaInteresse()) {
    				if (candidatura.getInteressado().getId() == usuarioAtual.getId()) {
    					novaFilaInteresse.remove(candidatura);
    					animal.setFilaInteresse(novaFilaInteresse);
    					adotanteEncontrado = true;
    					break;
    				}
    			}
    			if (!adotanteEncontrado) {
    				System.out.println("Você não aplicou para esse animal. Verifique o id na sua lista de processos de adoção.");    				
    			}
    			break;
    		} else {
    			System.out.println("Esse animal não existe.");
        		break;
    		}
    	}
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
            novoAnimal = new Cachorro(animalRepositorio.gerarId(), nome, cor, raca, (Guardiao) usuarioAtual, porte);
        } else if (tipo.equals("gato")) {
            System.out.println("Digite tipo de pelo do gato (CURTO, MEDIO, LONGO):");
            GatoPelo pelo = GatoPelo.valueOf(scanner.nextLine().toUpperCase());
            novoAnimal = new Gato(animalRepositorio.gerarId(), nome, cor, raca, (Guardiao) usuarioAtual, pelo);
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
