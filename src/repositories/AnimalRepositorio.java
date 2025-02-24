package src.repositories;

import src.enums.FilaInteresseStatus;
import src.models.Adotante;
import src.models.Animal;
import src.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnimalRepositorio {
	private List<Animal> animais = new ArrayList<>();
	private int ultimoId = 0;

	private static AnimalRepositorio instance;

	private AnimalRepositorio() {
	};

	public static AnimalRepositorio getInstance() {
		if (instance == null) {
			instance = new AnimalRepositorio();
		}
		return instance;
	}

	public void cadastrarAnimal(Animal animal) {
		animais.add(animal);
	}

	public Animal buscarAnimal(int id) {
		for (Animal animal : animais) {
			if (animal.getId() == id) {
				return animal;
			}
		}
		return null;
	}

	public void editarAnimal(int index, Animal animal) {
		if (index >= 0 && index < animais.size()) {
			animais.set(index, animal);
		}
	}

	public void excluirAnimal(Animal animal) {
		animais.remove(animal);
	}

	public void excluirAnimal(Animal animal, Usuario usuario) {
		if (animal.getGuardiao().getId() == usuario.getId()) {
			excluirAnimal(animal);
		} else {
			System.out.println(
					"Ação proibida pro usuário atual. Você não é guardião desse Animal. Nenhuma ação foi realizada.");
		}
	}

	public List<Animal> listarAnimais() {
		return animais;
	}

	public List<Animal> listarAnimais(String chave, String valor) {
		return listarAnimais(animais, chave, valor);
	}

	public List<Animal> listarAnimais(List<Animal> lista, String chave, String valor) {
		ArrayList<Animal> animaisFiltrados = new ArrayList<Animal>();
		Predicate<Animal> filtro = null;
		switch (chave) {
		case "status":
			if (valor == "disponível") {
				filtro = a -> a.getAdotante() == null;
			} else {
				filtro = a -> a.getAdotante() instanceof Adotante;
			}
			break;
		case "guardiao":
			filtro = a -> a.getGuardiao().getId() == Integer.getInteger(valor) && a.getAdotante() == null;
			break;
		case "candidatoAdotante":
			filtro = a -> a.getCandidatura(Integer.getInteger(valor)) != null
					&& a.getStatusFilaInteresse() == FilaInteresseStatus.ANALISE;
			break;
		case "nome":
			filtro = a -> a.getNome().toLowerCase().equals(valor);
			break;
		case "id":
			filtro = a -> a.getId() == Integer.getInteger(valor);
			break;
		case "cor":
			filtro = a -> a.getCor().toLowerCase().equals(valor);
			break;
		case "raça":
			filtro = a -> a.getRaca().toLowerCase().equals(valor);
			break;
		case "tipo":
			filtro = a -> a.getTipo().toLowerCase().equals(valor);
			break;
		case "cidade":
			filtro = a -> a.getGuardiao().getEndereco().getCidade().toLowerCase().equals(valor);
			break;
		case "estado":
			filtro = a -> a.getGuardiao().getEndereco().getEstado().toLowerCase().equals(valor);
			break;
		default:
			break;
		}

		if (filtro != null) {
			animaisFiltrados = (ArrayList<Animal>) animais.stream().filter(filtro).collect(Collectors.toList());
		}

		return (List<Animal>) animaisFiltrados;
	}

	public int gerarId() {
		ultimoId++; // Incrementa o ID
		return ultimoId; // Retorna o novo ID
	}
}
