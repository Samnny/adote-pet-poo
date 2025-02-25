package src.models;

public class Usuario {
	private int animaisAdotados = 0;
	private int finaisFelizes = 0;
	private int id;
	private String nome;
	private String login;
	private String senha;
	private Email email;
	private Telefone telefone;
	private Endereco endereco;
	
	Usuario(
		int id,
		String nome,
		String login,
		String senha,
		Email email,
		Telefone telefone,
		Endereco endereco
	) {
		this.id = id;
		this.setNome(nome);
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnimaisAdotados() {
		return animaisAdotados;
	}

	public void setAnimaisAdotados(int animaisAdotados) {
		this.animaisAdotados = animaisAdotados;
	}

	public int getFinaisFelizes() {
		return finaisFelizes;
	}

	public void setFinaisFelizes(int finaisFelizes) {
		this.finaisFelizes = finaisFelizes;
	};
}
