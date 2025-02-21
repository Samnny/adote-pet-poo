package models;

import enums.GatoPelo;

public class Gato extends Animal {
	private GatoPelo pelo;
	
	Gato(String nome, int id, String cor, String raca, GatoPelo pelo) {
		super(nome, id, cor, raca);
		this.setPelo(pelo);
	}
	
	public GatoPelo getPelo() {
		return pelo;
	}
	
	public void setPelo(GatoPelo pelo) {
		this.pelo = pelo;
	}
}
