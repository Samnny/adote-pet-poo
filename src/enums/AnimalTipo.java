package src.enums;

public enum AnimalTipo {
	ANIMAL(0), CACHORRO(1), GATO(2);
	
	public int tipo;
	
	private AnimalTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return tipo;
	}
}
