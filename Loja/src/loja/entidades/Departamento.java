package loja.entidades;

import java.util.ArrayList;

import ExceptionDAO.ExceptioDao;
import loja.dao.DepartamentoDao;

public class Departamento {

	private Integer ID_departamento;
	private String nome;
	
	

	public Departamento(Integer iD_departamento, String nome) {
		
		ID_departamento = iD_departamento;
		this.nome = nome;
	}
	public Departamento() {
		// TODO Auto-generated constructor stub
	}
	public Integer getID_departamento() {
		return ID_departamento;
	}
	public void setID_departamento(Integer iD_departamento) {
		ID_departamento = iD_departamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void insereDepartamento(Departamento departamento) throws ExceptioDao {
		new DepartamentoDao().insereDepartamento(departamento);
	}
	public void alteraDepartamento (Departamento departamento) throws ExceptioDao {
		new DepartamentoDao().alteraDepartamento(departamento);
	}
	public void apagaAtor (Departamento departamento) throws ExceptioDao {
		new DepartamentoDao().apagaDepartamento(departamento);
	}
	public ArrayList<Departamento> listarAtor (String nome) throws ExceptioDao{
		return new DepartamentoDao().listaDepartamentos(nome);
		
	}
	public ArrayList<Departamento> listarDep (){
		return new DepartamentoDao().listaDep();
	}
	
}
