package Controller;

import java.util.ArrayList;

import ExceptionDAO.ExceptioDao;
import loja.entidades.Departamento;

public class DepartamentoController {

	public boolean inserirDepartamento(String nome) throws ExceptioDao {

		if (nome != null && nome.length() > 0) {
			Departamento departamento = new Departamento(null, nome);
			departamento.insereDepartamento(departamento);

			return true;
		} else {

			return false;
		}
	}

	public boolean alteraDepartamento(Integer ID_departamento, String nome) throws ExceptioDao {

		if (nome != null && nome.length() > 0) {
			Departamento departamento = new Departamento(null, nome);
			departamento.setID_departamento(ID_departamento);
			departamento.alteraDepartamento(departamento);
			return true;
		}
		return false;
	}

	public boolean deletadepartamento(Integer ID_departamento) throws ExceptioDao {

		if (ID_departamento == 0) {
			return false;
		} else {
			Departamento departamento = new Departamento();
			departamento.setID_departamento(ID_departamento);
			departamento.apagaAtor(departamento);
			return true;
		}

	}

	public ArrayList<Departamento> listarDepartamentos(String nome) throws ExceptioDao {
		return new Departamento().listarAtor(nome);
	}

	public ArrayList<Departamento> listaDep() {
		return new Departamento().listarDep();
	}

}
