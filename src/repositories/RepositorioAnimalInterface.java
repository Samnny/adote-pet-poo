package src.repositories;

import src.models.Animal;

import java.util.List;

public interface RepositorioAnimalInterface {
    void cadastrarAnimal(Animal animal);

    Animal buscarAnimal(int id);

    void editarAnimal(int index, Animal animal);

    void excluirAnimal(Animal animal);

    List<Animal> listarAnimais();
}
