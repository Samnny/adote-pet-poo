package src.models;

import src.enums.CachorroPorte;

public class Cachorro extends Animal {
	private CachorroPorte porte;
	
	Cachorro(int id, String nome, String cor, String raca, Guardiao guardiao, CachorroPorte porte) {
		super(id, nome, cor, raca, guardiao);
		this.setPorte(porte);
	}

	public CachorroPorte getPorte() {
		return porte;
	}

	public void setPorte(CachorroPorte porte) {
		this.porte = porte;
	}
	
	public String getTipo() {
		return "CACHORRO";
	};
}
