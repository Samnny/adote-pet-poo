package src.models;

public abstract class Contato {
    protected String valor;

    public Contato(String valor) {
        this.valor = valor;
    }

    public abstract boolean validar();

    public String getValor() {
        return valor;
    }
}
