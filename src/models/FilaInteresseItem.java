package src.models;

public class FilaInteresseItem {
	private Adotante interessado;
	private String data;
	private String mensagem;
	
	FilaInteresseItem(Adotante interessado, String data, String mensagem) {
		this.interessado = interessado;
		this.data = data;
		this.mensagem = mensagem;
	}
	
	public void setInteressado(Adotante interessado) {
		this.interessado = interessado;
	}
	
	public Adotante getInteressado() {
		return interessado;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
}
