package src.models;

public class Guardiao extends Usuario {
	public Guardiao(
		int id,
		String nome,
		String login,
		String senha,
		Email email,
		Telefone telefone,
		Endereco endereco
	) {
    	super(id, nome, login, senha, email, telefone, endereco);	
    }

	public String getTipo() {
    	return "GUARDIAO";
    }
}
