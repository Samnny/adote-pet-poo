package src.enums;

public enum FilaInteresseStatus {
	ANALISE(0), FINALIZADO(1);
	
	private int status;
	
	private FilaInteresseStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
};
