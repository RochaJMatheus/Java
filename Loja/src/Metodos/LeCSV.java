package Metodos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionDao.Conexao;
import Controller.ClienteController;
import loja.entidades.Cliente;

public class LeCSV extends Thread {

	public void ler() {

		List<Cliente> lista;
		String arquivoCSV = "C:\\dados.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";

		try {

			br = new BufferedReader(new FileReader(arquivoCSV, StandardCharsets.UTF_8));
			int c = 0;
			lista = new ArrayList<Cliente>();

			while ((linha = br.readLine()) != null) {

				c++;

				if (c > 1) {
					String[] dados = linha.trim().split(csvDivisor);
					Cliente cli = new Cliente();
					cli.setCodCliente(Integer.parseInt(dados[0].trim()));
					cli.setNome(dados[1].trim());
					cli.setCPF(dados[2].trim());
					cli.setEmail(dados[3].trim());
					cli.setEndereco(dados[4].trim());
					cli.setDtNascimento(dados[5].trim());

					lista.add(cli);

				} else {

				}

			}

			for (Cliente cli : lista) {

				if (existePessoa(cli.getCodCliente(), cli.getNome(), cli.getCPF(), cli.getEmail(), cli.getEndereco(),
						cli.getDtNascimento()) != true) {

					ClienteController controller = new ClienteController();
					controller.InsereClientes(cli.getCodCliente(), cli.getNome(), cli.getCPF(), cli.getEmail(),
							cli.getEndereco(), cli.getDtNascimento());

					System.out.println(cli);

				} else {

					System.out.println("Dados Já Gravados");
					break;

				}
			}

		} catch (

		IOException ex) {
			ex.printStackTrace();

		}
	}

	public void atualizaCPF() {

		List<Cliente> lista;
		String arquivoCSV = "C:\\Locadora\\Loja\\src\\CPF.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";

		try {

			br = new BufferedReader(new FileReader(arquivoCSV, StandardCharsets.UTF_8));
			int c = 0;
			lista = new ArrayList<Cliente>();

			while ((linha = br.readLine()) != null) {

				c++;
				if (c > 1) {
					String[] dados = linha.trim().split(csvDivisor);
					Cliente cli = new Cliente();
					cli.setCodCliente(Integer.parseInt(dados[0].trim()));
					cli.setNome(dados[1].trim());
					cli.setCPF(dados[2].trim());
					cli.setEmail(dados[3].trim());
					cli.setEndereco(dados[4].trim());
					cli.setDtNascimento(dados[5].trim());
					lista.add(cli);

					// Imprimir os valores que o result set trouxe. (Funcionando)
					ArrayList<Cliente> a = TrazCPF((Integer.parseInt(dados[0])));
					// passando pelo array de validação que pega outro array como paramereo
					validarCPF(a);

					System.out.println(validarCPF(a));

					for (Cliente cliente : a) {
						executaUpdate(cliente.getCPF(), cliente.getCodCliente());
					}

				} else {

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void InsereTBCPF() {

		List<Cliente> lista;
		String arquivoCSV = "C:\\Locadora\\Loja\\src\\CPF.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";

		try (BufferedReader bufferedReader = br = new BufferedReader(
				new FileReader(arquivoCSV, StandardCharsets.UTF_8))) {

			int c = 0;
			lista = new ArrayList<Cliente>();

			while ((linha = br.readLine()) != null) {
				c++;
				if (c > 1) {
					String[] dados = linha.trim().split(csvDivisor);
					Cliente cli = new Cliente();

					cli.setCodCliente(Integer.parseInt(dados[0].trim()));
					cli.setNome(dados[1].trim());
					cli.setCPF(dados[2].trim());
					cli.setEmail(dados[3].trim());
					cli.setEndereco(dados[4].trim());
					cli.setDtNascimento(dados[5].trim());

					lista.add(cli);
					System.out.println(cli);
				}
			}

			for (Cliente cliente : lista) {
				if (existePessoa(cliente.getCodCliente(), cliente.getNome(), cliente.getCPF(), cliente.getEmail(),
						cliente.getEndereco(), cliente.getDtNascimento()) != true) {
					ClienteController controller = new ClienteController();
					controller.InsereTbCpf(cliente.getCodCliente(), cliente.getNome(), cliente.getCPF(),
							cliente.getEmail(), cliente.getEndereco(), cliente.getDtNascimento());
					System.out.println(cliente);
				} else {
					System.out.println("Novos dados ja inseridos ");
					break;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static boolean existePessoa(Integer codCliente, String nome, String CPF, String email, String endereco,
			String dtNascimento) {
		Connection conn = null;
		boolean existe = false;
		PreparedStatement pstm = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(
					"Select * from clientes where (codCliente=?) and (nome =?) and (CPF=?) and (email =?) and (endereco=?) and (dtNascimento=?)");
			pstm.setInt(1, codCliente);
			pstm.setString(2, nome);
			pstm.setString(3, CPF);
			pstm.setString(4, email);
			pstm.setString(5, endereco);
			pstm.setString(6, dtNascimento);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				existe = true;
			}
			pstm.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (final SQLException ex) {
				ex.printStackTrace();
			}

		}
		return existe;
	}

	public void InsereTbCpf(Cliente cliente) {

		String sql = "insert into atualizacpf  (codCliente, nome, CPF, Email, Endereco,deNascimento) values (?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cliente.getCodCliente());
			pstm.setString(2, cliente.getNome());
			pstm.setString(3, cliente.getCPF());
			pstm.setString(4, cliente.getEmail());
			pstm.setString(5, cliente.getEndereco());
			pstm.setString(6, cliente.getDtNascimento());
			pstm.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}

	}

	// Metodo criado para fazer o update do CPF fo cliente

	public void executaUpdate(String CPF, Integer codCliente) {

		String sql = "UPDATE clientes set CPF = ? where codCliente = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		// Cliente cli = new Cliente();
		try {
			conn = new Conexao().getConnection();

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, CPF);
			pstm.setInt(2, codCliente);
			pstm.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Metodo pra trazer todos os CPFs do banco de dados

	public static ArrayList<Cliente> TrazCPF(Integer codCliente) {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Cliente> cliente = null;
		try {

			conn = new Conexao().getConnection();
			pstm = conn
					.prepareStatement("Select codCliente,CPF from   clientes where  codCliente = " + codCliente + "");

			rs = pstm.executeQuery();

			if (rs != null) {
				cliente = new ArrayList<Cliente>();
				while (rs.next()) {

					Cliente cli = new Cliente();
					cli.setCodCliente(rs.getInt("codCliente"));
					// cli.setNome(rs.getString("nome"));
					cli.setCPF(rs.getString("CPF"));
					// cli.setEmail(rs.getString("email"));
					// cli.setEndereco(rs.getString("endereco"));
					// cli.setDtNascimento(rs.getString("dtNascimento"));

					cliente.add(cli);

				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return cliente;

	}

	public List<Cliente> validarCPF(List<Cliente> lista) {
		// ESSE METODO PEGA UMA LIST COMO PARAMETRO DELA QUE NESAS CASO SERIA UMA OUTRA
		// LISTA QUE CONTEM O CAMPO CPF E O CODIGO

		for (Cliente cliente : lista) {

			String cpf = cliente.getCPF();

			if (cpf.length() < 14) {
				String newCpf = ProcuraCPF(cliente.getCodCliente());
				cliente.setCPF(newCpf);
			}
		}

		return lista;

	}

	public String ProcuraCPF(Integer codigo) {

		// List<Cliente> lista;
		String arquivoCSV = "C:\\Locadora\\Loja\\src\\CPF.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		String CPF = "";
		try {

			br = new BufferedReader(new FileReader(arquivoCSV, StandardCharsets.UTF_8));
			int c = 0;

			while ((linha = br.readLine()) != null) {

				c++;

				// DIVIDIDO EM PARTES A PLANILHA PARA PEGAR OS CAMPOS

				if (c > 1) {
					String[] dados = linha.trim().split(csvDivisor);

					/*
					 * \\Cliente cli = new Cliente();
					 * cli.setCodCliente(Integer.parseInt(dados[0].trim()));
					 * cli.setNome(dados[1].trim()); cli.setCPF(dados[2].trim());
					 * cli.setEmail(dados[3].trim()); cli.setEndereco(dados[4].trim());
					 * cli.setDtNascimento(dados[5].trim()); lista.add(cli);
					 */

					if (dados[0].trim().equals(codigo + "")) {
						CPF = dados[2].trim();
						break;
					}

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return CPF;
	}

}
