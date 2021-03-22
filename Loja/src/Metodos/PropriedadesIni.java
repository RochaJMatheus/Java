package Metodos;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import LojaViews.LojaPrincipal;

public class PropriedadesIni {

	public void escreveini1(String arquivo, String chave, String iniescreve, String variavel)
			throws InvalidFileFormatException, IOException {

		int i = 0;
		for (int n = 1; n < i; n++) {
			Ini ini = new Ini(new File(arquivo));
			ini.add(chave);
			ini.put(chave, (String) iniescreve, variavel);

			ini.store();
		}

	}

	public void escreveini(String arquivo, String chave, String valor, String variavel)
			throws InvalidFileFormatException, IOException {

		Ini ini = new Ini(new File(arquivo));
		ini.add(chave);
		ini.put(chave, variavel, valor);

		ini.store();

	}

	public String leIni(String arquivo, String chave, String variavel) throws InvalidFileFormatException, IOException {

		String retorno = null;
		Ini ini = new Ini(new File(arquivo));

		retorno = ini.get("key", "1");

		return retorno;

	}

}
