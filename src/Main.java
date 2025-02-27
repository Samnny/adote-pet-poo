package src;

import src.models.SistemaAdocao;

public class Main {
	public static void main(String[] args) {
		SistemaAdocao sistema = SistemaAdocao.getInstance();
		sistema.iniciarSistema();
	}
}