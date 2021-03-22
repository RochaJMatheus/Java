package Controller;

import java.util.ArrayList;

import loja.entidades.Cliente;

public class ClienteController {

	public boolean InsereClientes(Integer codCliente, String nome, String CPF, String email, String endereco,
			String dtNascimento) {

		if (codCliente != null && nome != null && nome.length() > 0 && CPF != null && CPF.length() > 0 && email != null
				&& email.length() > 0 && endereco != null && endereco.length() > 0 && dtNascimento != null
				&& dtNascimento.length() > 0) {

			Cliente cli = new Cliente(codCliente, nome, CPF, email, endereco, dtNascimento);
			cli.InsereCliente(cli);
			return true;
		} else {
			return false;
		}

	}

	public boolean AlteraClientes(Integer codcliente, String nome, String CPF, String email, String endereco,
			String dtNascimento) {

		if (nome != null && nome.length() > 0 && CPF != null && CPF.length() > 0 && email != null && email.length() > 0
				&& endereco != null && endereco.length() > 0 && dtNascimento != null && dtNascimento.length() > 0) {

			Cliente cli = new Cliente(codcliente, nome, CPF, email, endereco, dtNascimento);
			cli.setCodCliente(codcliente);
			cli.AlteraCliente(cli);
			return true;
		}
		return false;

	}

	public boolean deletaCliente(Integer codCliente) {
		if (codCliente == null) {
			return false;
		} else {
			Cliente cli = new Cliente();
			cli.setCodCliente(codCliente);
			cli.apagaCliente(cli);
			return true;
		}

	}

	public ArrayList<Cliente> listaClientes() {
		return new Cliente().listaCliente();
	}

	public ArrayList<Cliente> listaClientes(ArrayList<Cliente> cliente) {
		return new Cliente().listaCliente();

	}

	public ArrayList<Cliente> listaClienteNome(String nome) {
		return new Cliente().listaClienteNome(nome);
	}

	public boolean alteraCPF(String cpf) {
		try {
			Cliente cli = new Cliente(cpf);
			
			cli.alteraCliente(cli);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;

	}
	
	public boolean InsereTbCpf (Integer codCliente, String nome, String CPF, String email, String endereco, String DtNAscimento) {
		if (codCliente ==0 || CPF == null  ) {
			return false ;
		}else {
			
			Cliente cli = new Cliente(codCliente,nome,CPF,email,endereco,DtNAscimento);
			
			cli.insereTbCpf(cli);
			return true ;
		}
	}

}
