package src.models;

public class Telefone extends Contato{
    private boolean whatsapp;
    private boolean ligacao;

    public Telefone(String valor, boolean whatsapp, boolean ligacao) {
        super(valor);
        this.setWhatsapp(whatsapp);
        this.setLigacao(ligacao);
    }

    @Override
    public boolean validar() {
        return valor.matches("\\d{10,11}");
    }

	public boolean isWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(boolean whatsapp) {
		this.whatsapp = whatsapp;
	}

	public boolean isLigacao() {
		return ligacao;
	}

	public void setLigacao(boolean ligacao) {
		this.ligacao = ligacao;
	}
}
