package Controller;

import java.util.ArrayList;

import ExceptionDAO.ExceptioDao;
import loja.entidades.Departamento;
import loja.entidades.Funcionarios;

public class FuncionariosController {

	public boolean insereFuncionarios(String nome, Integer idade, Double salario, Integer  id_departamento)
			throws ExceptioDao {
		if (nome != null && nome.length() > 0 && idade != null && salario > 0
				&& id_departamento  != null && id_departamento > 0 ) {
			
			Funcionarios funcionario = new Funcionarios(idade, nome, idade, salario, id_departamento);
			funcionario.insereFuncionarios(funcionario);
			return true;
		} else {
			return false;
		}
	}

	public boolean alteraFuncionario(Integer id_funcionarios, String nome, Integer idade,Double salario,
			Integer Id_departamento) throws ExceptioDao {
		if (nome != null && nome.length() > 0 && idade != null && salario > 0 && Id_departamento > 0
				&& Id_departamento != null) {

			Funcionarios funcionarios = new Funcionarios(id_funcionarios, nome, idade, salario, Id_departamento);
			funcionarios.setID_funcionarios(id_funcionarios);
			funcionarios.alteraFuncionario(funcionarios);
			return true;

		} else {
			return false;
		}
	}

	public boolean deletaFuncionarios(Integer ID_funcionarios) throws ExceptioDao {
		if (ID_funcionarios == 0) {
			return false;
		} else {
			Funcionarios funcionarios = new Funcionarios();
			funcionarios.setID_funcionarios(ID_funcionarios);
			funcionarios.deletarFuncoinarios(funcionarios);
			return true;
		}

	}
	public ArrayList<Funcionarios> listafuncionarios (int id  ) throws ExceptioDao{
		return  new Funcionarios().listaFuncionarios(id);
		
	}
	public ArrayList<Departamento> listaDepartamentos (){
		return new Funcionarios().listaDepartamento();
	}
	
}
