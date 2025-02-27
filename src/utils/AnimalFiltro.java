package src.utils;

import java.util.Scanner;

public class AnimalFiltro {
	private Scanner scanner = new Scanner(System.in);
	private String tipoFiltro;
	private String valorFiltro;
	
	public AnimalFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
	public void pergutarValorFiltro() {
		System.out.println("Escreva o valor do filtro " + tipoFiltro);
		setValorFiltro(scanner.nextLine());
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getValorFiltro() {
		return valorFiltro;
	}

	public void setValorFiltro(String valorFiltro) {
		this.valorFiltro = valorFiltro;
	}
}