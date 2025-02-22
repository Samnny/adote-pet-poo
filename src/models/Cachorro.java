package src.models;

import src.enums.CachorroPorte;

public class Cachorro extends Animal {
	private CachorroPorte porte;
	
	Cachorro(int id, String nome, String cor, String raca, CachorroPorte porte) {
		super(id, nome, cor, raca);
		this.setPorte(porte);
	}

	public CachorroPorte getPorte() {
		return porte;
	}

	public void setPorte(CachorroPorte porte) {
		this.porte = porte;
	}
}
