package src.models;
import src.enums.GatoPelo;

public class Gato extends Animal {
	private GatoPelo pelo;
	
	public Gato(int id, String nome, String cor, String raca, Guardiao guardiao, GatoPelo pelo) {
		super(id, nome, cor, raca, guardiao);
		this.setPelo(pelo);
	}
	
	public GatoPelo getPelo() {
		return pelo;
	}
	
	public void setPelo(GatoPelo pelo) {
		this.pelo = pelo;
	}
	
	public String getTipo() {
		return "GATO";
	}
}
