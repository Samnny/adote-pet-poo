package src.enums;

public enum CachorroPorte {
	MINI(0), PEQUENO(1), MEDIO(2), GRANDE(3);
	
	private int porte;
	
	private CachorroPorte(int porte) {
		this.porte = porte;
	}
	
	public int getPorte() {
		return porte;
	}
}
