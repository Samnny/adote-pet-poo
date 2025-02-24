package src.models;

import src.enums.CachorroPorte;
import src.enums.GatoPelo;
import src.repositories.UsuarioRepositorio;
import utils.AnimalFiltro;
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
    
    private void exibirListaAnimais(List<Animal> animais) {
    	for (Animal animal : animais) {
    		System.out.println(animal.toString());
            System.out.println("Raça do Animal: " + animal.getRaca());
            System.out.println("Cor do Animal: " + animal.getCor());
            System.out.println("Espécie do Animal: " + animal.getTipo());
        }
    }

    private void buscarPets() {
        List<Animal> animaisDisponiveis = animalRepositorio.listarAnimais("status", "disponível");
        exibirListaAnimais(animaisDisponiveis);
        menuBuscarPetsDisponiveis(animaisDisponiveis);
    }
    
    private void menuBuscarPetsDisponiveis(List<Animal> animaisDisponiveis) {
    	while (true) {
    		System.out.println("1. Filtrar resultados\n2.Selecionar pet\n3.Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 1) {
    			menuFiltrarPets(animaisDisponiveis);
    		} else if (escolha == 2) {
    			menuSelecionarPet(animaisDisponiveis);
    		} else {
    			break;
    		}
    	}
    }
    
    private void menuSelecionarPet() {
    	
    }
    
    private void menuFiltrarPets(List<Animal> animais) {
    	while (true) {
    		System.out.println("Filtrar animais por:");
    		System.out.println("1. Nome\n2. Id\n3. Espécie(GATO, CACHORRO)\n4. Cor");
    		System.out.println("5. Raça\n6. Cidade\n7. Estado\n8. Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 8) {
    			break;
    		} else {
    			AnimalFiltro filtro;
    			switch(escolha) {
    				case 1:
    					filtro = new AnimalFiltro("nome");
						filtro.pergutarValorFiltro();
    					exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 2:
    					filtro = new AnimalFiltro("id");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 3:
    					filtro = new AnimalFiltro("tipo");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 4:
    					filtro = new AnimalFiltro("cor");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 5:
    					filtro = new AnimalFiltro("raça");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 6:
    					filtro = new AnimalFiltro("cidade");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 7:
    					filtro = new AnimalFiltro("estado");
						filtro.pergutarValorFiltro();
						exibirListaAnimais(animalRepositorio.listarAnimais(animais, filtro.getTipoFiltro(), filtro.getValorFiltro()));
    					break;
    				case 8:
					default:
    					break;
    			}
    		}
    	}
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
    
    public void menuSelecionarPet() {}

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
