package src.enums;

public enum GatoPelo {
	CURTO(0), LONGO(1);
	
	private int pelo;
	
	private GatoPelo(int pelo) {
		this.pelo = pelo;
	}
	
	public int getPelo() {
		return pelo;
	}
}
