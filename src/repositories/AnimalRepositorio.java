package src.repositories;

import src.enums.FilaInteresseStatus;
import src.models.Adotante;
import src.models.Animal;
import src.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
				"Ação proibida pro usuário atual. Você não é guardião desse Animal. Nenhuma ação foi realizada."
			);
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
		Function<String, Predicate<Animal>> filtro = null;
		switch (chave) {
		case "status":
			if (valor == "disponível") {
				filtro = v -> a -> a.getAdotante() == null;
			} else {
				filtro = v -> a -> a.getAdotante() instanceof Adotante;
			}
			break;
		case "guardiao":
			filtro = v -> a -> a.getGuardiao().getId() == Integer.parseInt(v) && a.getAdotante() == null;
			break;
		case "candidatoAdotante":
			filtro = v -> a -> a.getCandidatura(Integer.parseInt(v)) != null
					&& (a.getStatusFilaInteresse() == FilaInteresseStatus.ANALISE || a.getAdotante().getId() == Integer.parseInt(v));
			break;
		case "nome":
			filtro = v -> a -> a.getNome().toLowerCase().equals(v);
			break;
		case "id":
			filtro = v -> a -> a.getId() == Integer.parseInt(v);
			break;
		case "cor":
			filtro = v -> a -> a.getCor().toLowerCase().equals(v);
			break;
		case "raça":
			filtro = v -> a -> a.getRaca().toLowerCase().equals(v);
			break;
		case "tipo":
			filtro = v -> a -> a.getTipo().toLowerCase().equals(v);
			break;
		case "cidade":
			filtro = v -> a -> a.getGuardiao().getEndereco().getCidade().toLowerCase().equals(v);
			break;
		case "estado":
			filtro = v -> a -> a.getGuardiao().getEndereco().getEstado().toLowerCase().equals(v);
			break;
		default:
			break;
		}

		if (filtro != null) {
			animaisFiltrados = (ArrayList<Animal>) animais.stream()
				.filter(
					filtro.apply(valor)
				)
				.collect(Collectors.toList());
		}

		return (List<Animal>) animaisFiltrados;
	}

	public int gerarId() {
		ultimoId++; // Incrementa o ID
		return ultimoId; // Retorna o novo ID
	}
}
