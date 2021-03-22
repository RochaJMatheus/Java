package ThreadImport;

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
import loja.entidades.Cliente;

public class AtualizaCpf implements Runnable {

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

	@Override
	public void run() {
		atualizaCPF();
	}
}
