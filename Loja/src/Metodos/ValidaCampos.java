package Metodos;

import javax.swing.JOptionPane;

public class ValidaCampos {

	

	public boolean validaCamposDep(String Nome) {

		if (Nome.equals("")) {

			JOptionPane.showMessageDialog(null, "Preecha todos os campos e prossiga ");
			return true;

		} else {
			return false;
		}

	}

	public boolean validaCamposFuncionario(String nome, String idade, String salario, String ID_dep) {

		if (nome.equals("") || nome.length() <= 0 || idade.equals("") || idade.length() <= 0 || salario.equals("")
				|| salario.length() <= 0 || ID_dep.equals("") || ID_dep.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Preecha todos os campos para prosseguir!!! ");
			return true;

		} else {

		}
		return false;
	}

}
