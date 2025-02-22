package src.repositories;

import src.models.Animal;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepositorio {
    private List<Animal> animais = new ArrayList<>();
    private int ultimoId = 0;

    public void cadastrarAnimal(Animal animal) {
        animal.setId(gerarId());
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

    public int gerarId() {
        ultimoId++;  // Incrementa o ID
        return ultimoId; // Retorna o novo ID
    }
}
