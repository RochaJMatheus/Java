package loja.entidades;

import java.util.ArrayList;

import ExceptionDAO.ExceptioDao;
import loja.dao.FuncionariosDAO;

public class Funcionarios {
	private Integer ID_funcionarios;
	private String nome;
	private Integer idade;
	private Double salario;
	private Integer Id_departamento;

	
	
		
	

	public Funcionarios(Integer iD_funcionarios, String nome, Integer idade, Double salario,
			Integer Id_departamento) {
		
		ID_funcionarios = iD_funcionarios;
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
		this.Id_departamento = Id_departamento;
	}
	public Funcionarios() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getID_funcionarios() {
		return ID_funcionarios;
	}
	public void setID_funcionarios(Integer iD_funcionarios) {
		ID_funcionarios = iD_funcionarios;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Integer  getId_Departamento() {
		return Id_departamento;
	}
	public void setId_Departamento(Integer Id_departamento) {
		this.Id_departamento = Id_departamento;
	}
	
	public void insereFuncionarios(Funcionarios funcionario) throws ExceptioDao {
		new FuncionariosDAO().insereFuncionario(funcionario);
	}
	public void alteraFuncionario(Funcionarios funcionario) throws ExceptioDao {
		new FuncionariosDAO().alteraFuncionario(funcionario);
	}
	public void deletarFuncoinarios(Funcionarios funcionario) throws ExceptioDao {
		new FuncionariosDAO().deletafuncionarios(funcionario);
	}
	public ArrayList<Funcionarios> listaFuncionarios (int id  ) throws ExceptioDao{
		return new FuncionariosDAO().listaFuncionarios(id);
	}
	public ArrayList<Departamento> listaDepartamento (){
		return new FuncionariosDAO().listaDep();
	}

}
