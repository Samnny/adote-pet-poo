package src.models;

public class Telefone extends Contato{
    private boolean whatsapp;
    private boolean ligacao;

    public Telefone(String valor, boolean whatsapp, boolean ligacao) {
        super(valor);
        this.whatsapp = whatsapp;
        this.ligacao = ligacao;
    }

    @Override
    public boolean validar() {
        return valor.matches("\\d{10,11}");
    }
}
