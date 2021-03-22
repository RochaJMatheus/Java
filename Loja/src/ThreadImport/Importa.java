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
import Controller.ClienteController;
import loja.entidades.Cliente;

public class Importa implements Runnable {

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

	@Override

	public void run() {
		ler();

	}

}
