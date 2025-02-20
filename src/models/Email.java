package src.models;

public class Email extends Contato{
    public Email(String valor) {
        super(valor);
    }

    @Override
    public boolean validar() {
        return valor.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
