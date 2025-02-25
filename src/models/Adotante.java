package src.models;

public class Adotante extends Usuario {
	
    public Adotante(
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
	
	public void contatar() {
		System.out.println("E-mail do usuário: " + getEmail().getValor());
		System.out.println("Telefone do usuário: " + getTelefone().getValor());
		System.out.println("Preferências de contato do usuário: ");
		if (getTelefone().isWhatsapp()) {
			System.out.println("(x) WhatsApp");				
		} else {
			System.out.println("( ) WhatsApp");
		}
		if (getTelefone().isLigacao()) {
			System.out.println("(x) Ligação");				
		} else {
			System.out.println("( ) Ligação");
		}
	}

	public String getTipo() {
    	return "ADOTANTE";
    }
}
