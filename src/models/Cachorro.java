package models;

import enums.CachorroPorte;

public class Cachorro extends Animal {
	private CachorroPorte porte;
	
	Cachorro(String nome, int id, String cor, String raca, CachorroPorte porte) {
		super(nome, id, cor, raca);
		this.setPorte(porte);
	}

	public CachorroPorte getPorte() {
		return porte;
	}

	public void setPorte(CachorroPorte porte) {
		this.porte = porte;
	}
}
