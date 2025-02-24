package src.models;

public class Guardiao extends Usuario {
	private int finaisFelizes = 0;
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

    public int getFinaisFelizes() {
		return finaisFelizes;
	}

	public void setFinaisFelizes(int finaisFelizes) {
		this.finaisFelizes = finaisFelizes;
	}

	public String getTipo() {
    	return "GUARDIAO";
    }
}
