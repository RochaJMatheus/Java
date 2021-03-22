package Main;

import java.io.IOException;

import org.ini4j.InvalidFileFormatException;

import LojaViews.LojaPrincipal;
import Metodos.PropriedadesIni;

public class Main {
	
	public static void main(String[] args) throws InvalidFileFormatException, IOException {

		LojaPrincipal tela = new LojaPrincipal();
		tela.abreTela(true);

		// LeCSV ler = new LeCSV();
		// ler.ler();
		
		
		System.out.println(new PropriedadesIni().leIni("C:\\Locadora\\Loja\\src\\prop.INI.txt", "key", "1"));
		System.out.println(new PropriedadesIni().leIni("C:\\Locadora\\Loja\\src\\clientesProp.INI.txt", "key", "1"));

	}
}
