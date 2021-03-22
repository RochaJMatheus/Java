package loja.entidades;

import java.util.ArrayList;

import Metodos.LeCSV;
import loja.dao.ClienteDAO;

public class Cliente {

	private Integer codCliente;
	private String nome;
	private String CPF;
	private String email;
	private String endereco;
	private String dtNascimento;
	
	public Cliente() {

	}
	public Cliente(String CPF) {
		this.CPF = CPF;
	}
	
	public Cliente(Integer codCliente, String nome, String cPF, String email, String endereco, String dtNascimento) {

		this.codCliente = codCliente;
		this.nome = nome;
		CPF = cPF;
		this.email = email;
		this.endereco = endereco;
		this.dtNascimento = dtNascimento;
	}

	

	public Cliente(Integer codCliente, String CPF) {
		this.CPF = CPF;
		this.codCliente = codCliente;
	}
	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer string) {
		this.codCliente = string;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public void InsereCliente (Cliente cliente ) {
		new ClienteDAO().insereCliente(cliente);
	}
	
	public void AlteraCliente (Cliente cliente ) {
		new ClienteDAO().updateCliente(cliente);
	}
	
	public ArrayList<Cliente> listaCliente (){
		return new ClienteDAO().listaClientes();
	}
	
	public void apagaCliente(Cliente cliente ) {
		new ClienteDAO().apagaCliente(cliente);
	}
	
	public ArrayList<Cliente> listaClienteNome(String nome){
		return new ClienteDAO().listaClientesNome(nome);
	}
	
	public void alteraCliente (Cliente cli){
		new ClienteDAO().AlteraCpf(cli);
	}
	public void insereTbCpf(Cliente cli ) {
		new LeCSV().InsereTbCpf(cli);
	}
	

	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome + ", CPF=" + CPF + ", email=" + email
				+ ", endereco=" + endereco + ", dtNascimento=" + dtNascimento + "]";
	}
	
	
	
	
}
