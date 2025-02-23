package src.models;

import src.enums.FilaInteresseStatus;

import java.util.ArrayList;

public abstract class Animal {
	private String nome;
	private int id;
	private Adotante adotadoPor;
	private String cor;
	private String raca;
	private ArrayList<FilaInteresseItem> filaInteresse;
	private FilaInteresseStatus statusFilaInteresse;
	
	Animal(
			int id,
			String nome,
			String cor,
			String raca
	) {
		this.id = id;
		this.nome = nome;
		this.cor = cor;
		this.raca = raca;
		filaInteresse = new ArrayList<FilaInteresseItem>();
		statusFilaInteresse = FilaInteresseStatus.ANALISE;
	}
	
	@Override
	public String toString() {
		return "Id do Animal: #" + getId() + "\n" +
			   "Nome do Animal: " + getNome();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setAdotante(Adotante adotante) {
		this.adotadoPor = adotante;
	}
	public Adotante getAdotante() {
		return adotadoPor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getCor() {
		return cor;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getRaca() {
		return raca;
	}
	public void setFilaInteresse(ArrayList<FilaInteresseItem> filaInteresse) {
		this.filaInteresse = filaInteresse;
	}
	public ArrayList<FilaInteresseItem> getFilaInteresse() {
		return filaInteresse;
	}
	public void setStatusFilaInteresse(FilaInteresseStatus statusFilaInteresse) {
		this.statusFilaInteresse = statusFilaInteresse;
	}
	public FilaInteresseStatus getStatusFilaInteresse() {
		return statusFilaInteresse;
	}
}
