package src.models;

import src.enums.FilaInteresseStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Animal {
	private String nome;
	private int id;
	private Adotante adotadoPor;
	private String cor;
	private String raca;
	private Guardiao guardiao;
	private ArrayList<FilaInteresseItem> filaInteresse;
	private FilaInteresseStatus statusFilaInteresse;
	
	Animal(
			int id,
			String nome,
			String cor,
			String raca,
			Guardiao guardiao
	) {
		this.id = id;
		this.nome = nome;
		this.cor = cor;
		this.raca = raca;
		this.guardiao = (Guardiao) guardiao;
		filaInteresse = new ArrayList<FilaInteresseItem>();
		statusFilaInteresse = FilaInteresseStatus.ANALISE;
	}
	
	public abstract String getTipo();

	@Override
	public String toString() {
		return "Id do Animal: #" + getId() + "\n" +
			   "Nome do Animal: " + getNome();
	}
	
	public void exibirTodasInformacoes() {
		System.out.println(this.toString());
        System.out.println("Raça do Animal: " + getRaca());
        System.out.println("Cor do Animal: " + getCor());
        System.out.println("Espécie do Animal: " + getTipo());
	}
	
	public void exibirInformacoesFilaInteresse() {
		System.out.print(getId() + "  --  " + getNome() + "  --  ");
    	System.out.println(Integer.toString(getFilaInteresse().size()) + " candidatos");
	}
	
	public void ordenaFilaInteresse() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		filaInteresse.sort(Comparator.comparing(
			item -> ZonedDateTime.parse(item.getData(), formatter)
		));
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
	public FilaInteresseItem getCandidatura(int idAdotanteInteressado) {
		for (FilaInteresseItem candidatura : getFilaInteresse()) {
			if (candidatura.getInteressado().getId() == idAdotanteInteressado) {
				return candidatura;
			}
		}
		return null;
	}
	public void exibirDetalhesCandidatura(FilaInteresseItem candidatura) {
		System.out.println(this.toString());
		System.out.print("Status da Fila de Interesse: ");
		System.out.println(this.getStatusFilaInteresse());
		System.out.print("Data da sua aplicação: ");
		System.out.println(candidatura.getData());
		System.out.print("Sua mensagem de aplicação: ");
    	System.out.println(candidatura.getMensagem());
    	System.out.println("==================================");
	}
	public void addToFilaInteresse(FilaInteresseItem filaInteresse) {
		this.filaInteresse.add(filaInteresse);
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
	public Guardiao getGuardiao() {
		return guardiao;
	}
	public void setGuardiao(Guardiao guardiao) {
		this.guardiao = guardiao;
	}

	
}
