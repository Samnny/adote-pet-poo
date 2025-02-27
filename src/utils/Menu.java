package src.utils;

import src.models.Usuario;
import src.repositories.AnimalRepositorio;
import src.repositories.UsuarioRepositorio;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import src.enums.CachorroPorte;
import src.enums.FilaInteresseStatus;
import src.enums.GatoPelo;
import src.models.Adotante;
import src.models.Animal;
import src.models.Cachorro;
import src.models.FilaInteresseItem;
import src.models.Gato;
import src.models.Guardiao;
import src.models.SistemaAdocao;

public class Menu {
	private static String tipoUsuarioAtual;
	private static AnimalRepositorio animalRepositorio = AnimalRepositorio.getInstance();
	private static UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstance();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void MenuUsuario(Adotante adotante) {
		while (true) {
			System.out.println("Menu - Adotante");
            System.out.println("1. Buscar Pets para Adoção\n2. Acompanhar Adoções\n3. Voltar");
            int escolha = Menu.scanner.nextInt();
            Menu.scanner.nextLine();
            
            tipoUsuarioAtual = adotante.getTipo();

            if (escolha == 1) {
                MenuBuscarPets(adotante);
            } else if (escolha == 2) {
            	MenuListarFilasInteresse(adotante);
            } else {
                break;
            }
        }
	}

	public static void MenuUsuario(Guardiao guardiao) {
		while (true) {
			System.out.println("Menu - Guardião");
            System.out.println("1. Adicionar Pet\n2. Listar Filas de Interesse\n3. Listar Meus Pets\n4. Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            tipoUsuarioAtual = guardiao.getTipo();

            if (escolha == 1) {
            	MenuAdicionarPet(guardiao);
            } else if (escolha == 2) {
            	MenuListarFilasInteresse(guardiao);
            } else if (escolha == 3) {
            	MenuBuscarPets(guardiao);
            } else {
                break;
            }
        }
	}
	
	private static void MenuAdicionarPet(Guardiao guardiao) {
		System.out.println("Menu - Adicionar pet");

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
        	ArrayList<String> opcoes = new ArrayList<String>(Arrays.asList("PEQUENO", "MEDIO", "GRANDE"));
            while (true) {
            	System.out.println("Digite porte do cachorro (PEQUENO, MEDIO, GRANDE):");
            	String escolha = scanner.nextLine().toUpperCase();
            	if (!opcoes.contains(escolha.toUpperCase())) {
            		System.out.println("Opção de porte inválida.");
            		continue;
            	}
            	CachorroPorte porte = CachorroPorte.valueOf(escolha);
            	novoAnimal = new Cachorro(animalRepositorio.gerarId(), nome, cor, raca, guardiao, porte);
            	break;
            }
        } else if (tipo.equals("gato")) {
        	ArrayList<String> opcoes = new ArrayList<String>(Arrays.asList("CURTO", "MEDIO", "LONGO"));
            while (true) {
            	System.out.println("Digite tipo de pelo do gato (CURTO, MEDIO, LONGO):");
            	String escolha = scanner.nextLine().toUpperCase();
            	if (!opcoes.contains(escolha.toUpperCase())) {
            		System.out.println("Opção de pelo inválida.");
            		continue;
            	}
            	GatoPelo pelo = GatoPelo.valueOf(escolha);
            	novoAnimal = new Gato(animalRepositorio.gerarId(), nome, cor, raca, guardiao, pelo);
            	break;
            }
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        animalRepositorio.cadastrarAnimal(novoAnimal);
        System.out.println("Pet adicionado com sucesso!");
	}
	
	private static void MenuBuscarPets(Adotante adotante) {
        List<Animal> animaisDisponiveis = animalRepositorio.listarAnimais("status", "disponível");
        MenuBuscarPets(animaisDisponiveis);
	}
	
	private static void MenuBuscarPets(Guardiao guardiao) {
		List<Animal> animaisDoGuardiao = animalRepositorio.listarAnimais("guardiao", Integer.toString(guardiao.getId()));
		MenuBuscarPets(animaisDoGuardiao);
	}
	
	private static void MenuBuscarPets(List<Animal> animais) {
		System.out.println("Menu - Buscar pets");
		exibirListaAnimais(animais);
        
        while (true) {
    		System.out.println("1. Filtrar resultados\n2. Selecionar pet\n3. Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 1) {
    			MenuFiltrarPets(animais);
    		} else if (escolha == 2) {
    			MenuSelecionarPet(animais);
    		} else {
    			break;
    		}
    	}
	}
	
	private static void MenuFiltrarPets(List<Animal> animais) {
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

	private static void MenuSelecionarPet(List<Animal> animais) {
		System.out.println("Menu - Selecionar pet");
		System.out.println("Digite o id do pet que deseja selecionar: ");
		
		String id = scanner.nextLine();
		List<Animal> petBusca = animalRepositorio.listarAnimais(animais, "id", id);
		if (petBusca.isEmpty()) {
			System.out.println("Não foi encontrado animal com esse id.");
		} else {
			Animal petSelecionado = petBusca.get(0);
			MenuPetSelecionado(petSelecionado);    			
		}
	}
	
	private static void MenuPetSelecionado(Animal pet) {
		System.out.println("Menu - Pet selecionado");
		pet.exibirTodasInformacoes();
		
		if (tipoUsuarioAtual == "ADOTANTE") {
			System.out.println("1. Entrar para fila de interesse\n2. Voltar");
			int escolha = scanner.nextInt();
			scanner.nextLine();
			
			if (escolha == 1) {
				Adotante adotante = (Adotante) SistemaAdocao.getUsuarioAtual();
				List<FilaInteresseItem> adotanteEncontradoNaListaDoPet = pet.getFilaInteresse().stream().filter(item -> item.getInteressado().equals(adotante)).collect(Collectors.toList());
				if (adotanteEncontradoNaListaDoPet.isEmpty()) {
					System.out.println("Digite uma pequena descrição sobre você e porque se interessou por " + pet.getNome() + ". (Deixe vazio para cancelar a inscrição e voltar)");
					String mensagem = scanner.nextLine();
					if (!mensagem.isBlank()) {
						pet.addToFilaInteresse(
							new FilaInteresseItem((Adotante) SistemaAdocao.getUsuarioAtual(), ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toString(), mensagem)
						);
						System.out.println("Sucesso ao entrar pra fila de interesse.");
					}
				} else {
					System.out.println("Você já faz parte dessa fila de interesse.");
				}
			}
		} else if (tipoUsuarioAtual == "GUARDIAO") {
			boolean acaoConcluida = false;
			while(!acaoConcluida) {
				System.out.println("1. Excluir pet\n2. Visualizar fila de interesse.\n3. Voltar");
				int escolha = scanner.nextInt();
				scanner.nextLine();
				
				if (escolha == 1) {
					System.out.println("Certeza que deseja excluir " + pet.getNome() + "? (s)im/(N)ão");
					String confirma = scanner.nextLine().toLowerCase();
					char selecao = confirma.charAt(0);
					
					switch(selecao) {
						case 's':
							System.out.println("Excluindo pet " + pet.getNome() + " #" + Integer.toString(pet.getId()));
							animalRepositorio.excluirAnimal(pet, SistemaAdocao.getUsuarioAtual());
							acaoConcluida = true;
							break;
						case 'n':
							System.out.println("Voltando ao menu do pet " + pet.getNome());
							break;
						default:
							System.out.println("Seleção inválida. " + pet.getNome() + " não será excluído.");
							break;
					}
				} else if (escolha == 2) {
					MenuListarFilasInteresse((Guardiao) SistemaAdocao.getUsuarioAtual());
				} else {
					break;
				}
			}
		}
	}

	private static void MenuListarFilasInteresse(Adotante adotante) {
        System.out.println("Menu - Acompanhar processos de adoção");
        List<Animal> animais = animalRepositorio.listarAnimais("candidatoAdotante", Integer.toString(SistemaAdocao.getUsuarioAtual().getId()));
        if (animais.isEmpty()) {
        	System.out.println("Você não tem processos de adoção");
        } else {
        	for (Animal animal : animais) {
        		FilaInteresseItem candidatura = animal.getCandidatura(SistemaAdocao.getUsuarioAtual().getId());
        		if (animal.getStatusFilaInteresse() == FilaInteresseStatus.FINALIZADO) {
        			System.out.println("Você foi selecionado como adotante desse pet.");
        		}
        		animal.exibirDetalhesCandidatura(candidatura);
        	}        	
        }

        while (true) {
    		System.out.println("1. Escolher processo de adoção para cancelar\n2. Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 1) {
    			CancelarProcessoAdocao();
    		} else {
    			break;
    		}
    	}
    }

	private static void MenuListarFilasInteresse(Guardiao guardiao) {
		System.out.println("Menu - Listar filas de interesse");
        List<Animal> animaisDoGuardiao = animalRepositorio.listarAnimais("guardiao", Integer.toString(SistemaAdocao.getUsuarioAtual().getId()));
        if (animaisDoGuardiao.isEmpty()) {
        	System.out.println("Não existem animais cadastrados.");
        }
        for (Animal animal : animaisDoGuardiao) {
        	animal.exibirInformacoesFilaInteresse();
        }
        while(true) {
    		System.out.println("1. Filtrar filas de interesse\n2. Escolher fila de interesse\n3. Voltar");
    		
    		int escolha = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (escolha == 1) {
    			MenuFiltrarFilasInteresse(animaisDoGuardiao);
    		} else if (escolha == 2) {
    			MenuSelecionarFilaInteresse(animaisDoGuardiao);
    		} else {
    			break;
    		}
        }
    }
	
	private static void MenuFiltrarFilasInteresse(List<Animal> animais) {
		System.out.println("Menu - Filtrar filas de interesse");
		System.out.println("1. Exibir filas não-vazias\n2. Exibir animais com filas vazias\n3. Voltar");
    		
		int escolha = scanner.nextInt();
		scanner.nextLine();
		
		if (escolha == 1) {
			for (Animal animal : animais) {
				if (!animal.getFilaInteresse().isEmpty()) {
		        	animal.exibirInformacoesFilaInteresse();
				}
			}
		} else if (escolha == 2) {
			for (Animal animal : animais) {
				if (animal.getFilaInteresse().isEmpty()) {
		        	animal.exibirInformacoesFilaInteresse();
				}
			}
		} else {
			System.out.println("Opção inválida. Voltando ao menu anterior.");
		}
    }
	
	private static void MenuSelecionarFilaInteresse(List<Animal> animais) {
		System.out.println("Menu - Selecionar fila de interesse");
		System.out.println("Digite o id do pet que deseja selecionar a fila de interesse: ");
		
		String id = scanner.nextLine();
		List<Animal> petBusca = animalRepositorio.listarAnimais(animais, "id", id);
		if (petBusca.isEmpty()) {
			System.out.println("Não foi encontrado animal com esse id.");
		} else {
			Animal petSelecionado = petBusca.get(0);
			MenuFilaInteresseParaPetSelecionado(petSelecionado);
		}
	}
	
	private static void MenuFilaInteresseParaPetSelecionado(Animal animal) {
		boolean finalizado = false;
		while (!finalizado) {
			animal.exibirInformacoesFilaInteresse();
			animal.ordenaFilaInteresse();
			
			for (FilaInteresseItem candidatura : animal.getFilaInteresse()) {
				Usuario interessado = candidatura.getInteressado();
				System.out.println("Id usuário interessado: " + Integer.toString(interessado.getId()));
				System.out.println("Nome usuário interessado: " + interessado.getNome());
				System.out.println("Mensagem da aplicação: " + candidatura.getMensagem());
				((Adotante) candidatura.getInteressado()).contatar();
				System.out.println("Cidade do usuário: " + interessado.getEndereco().getCidade());
				System.out.println("Estado do usuário: " + interessado.getEndereco().getEstado());
				
				System.out.println("===========================");
			}

    		System.out.println("1. Finalizar fila de interesse\n2. Voltar");
    		
    		int escolha = scanner.nextInt();
			scanner.nextLine();
			
    		if (escolha == 1) {
    			finalizado = MenuFinalizarFilaInteresse(animal);
    		} else {
    			finalizado = true;;
    		}
    	}
	}
	
	private static boolean MenuFinalizarFilaInteresse(Animal animal) {
		System.out.println("Menu - Finalizar fila de interesse");
		System.out.println("1. Escolher um adotante\n2. Finalizar sem adotante\n3. Voltar");
    		
		int escolha = scanner.nextInt();
		scanner.nextLine();
			
		if (escolha == 1) {
			System.out.println("Escolha o id do usuário que deseja escolher como adotante de " + animal.getNome());
			System.out.println("(Deixe vazio para cancelar a ação)");
			
			String idAdotante = scanner.nextLine();
			
			if (idAdotante.isBlank()) {
				System.out.println("Cancelando operação.");
				return false;
			}
			
			Usuario adotante = usuarioRepositorio.buscarUsuario(Integer.parseInt(idAdotante));
			
			if (adotante == null) {
				System.out.println("Adotante não encontrado. Tente novamente.");
				return false;
			} else {
				animal.setStatusFilaInteresse(FilaInteresseStatus.FINALIZADO);
				animal.setAdotante((Adotante) new Adotante(
					adotante.getId(),
					adotante.getNome(),
        			adotante.getLogin(),
        			adotante.getSenha(),
        			adotante.getEmail(),
        			adotante.getTelefone(),
        			adotante.getEndereco()
    			));
				adotante.setAnimaisAdotados(adotante.getAnimaisAdotados() + 1);
				Usuario usuarioAtual = SistemaAdocao.getUsuarioAtual();
				usuarioAtual.setFinaisFelizes(usuarioAtual.getFinaisFelizes() + 1);
				System.out.println("Sucesso ao finalizar fila de interesse com adotante selecionado");
				return true;
			}
		}
		return true;
	}
	
	private static void CancelarProcessoAdocao() {
		System.out.println("Menu - Cancelar processo de adoção");
		System.out.println("Digite o id do animal do qual deseja sair da fila de interesse:");
		
		int escolha = scanner.nextInt();
		scanner.nextLine();
		
		Animal animal = animalRepositorio.buscarAnimal(escolha);
		
		if (animal != null) {
			ArrayList<FilaInteresseItem> novaFilaInteresse = new ArrayList<FilaInteresseItem>();
			novaFilaInteresse = animal.getFilaInteresse();
			boolean adotanteEncontrado = false;
			for (FilaInteresseItem candidatura : animal.getFilaInteresse()) {
				if (candidatura.getInteressado().getId() == SistemaAdocao.getUsuarioAtual().getId()) {
					novaFilaInteresse.remove(candidatura);
					animal.setFilaInteresse(novaFilaInteresse);
					adotanteEncontrado = true;
					break;
				}
			}
			if (!adotanteEncontrado) {
				System.out.println("Você não aplicou para esse animal. Verifique o id na sua lista de processos de adoção.");    				
			} else {
				System.out.println("Sucesso ao sair da fila de interesse");
			}
		} else {
			System.out.println("Esse animal não existe.");
		}
    }

	private static void exibirListaAnimais(List<Animal> animais) {
		if (animais.isEmpty()) {
			System.out.println("Nenhum pet encontrado.");
		} else {
			for (Animal animal : animais) {
				animal.exibirTodasInformacoes();
			}			
		}
		
	}

	public static String getTipoUsuarioAtual() {
		return tipoUsuarioAtual;
	}
	public static void setTipoUsuarioAtual(String tipoUsuarioAtual) {
		Menu.tipoUsuarioAtual = tipoUsuarioAtual;
	}
}
