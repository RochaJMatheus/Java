package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionDao.Conexao;
import loja.entidades.Cliente;

public class ClienteDAO {

	public void insereCliente(Cliente cliente) {

		String sql = "insert into clientes (codCliente, nome, CPF, email, endereco,dtNascimento) values (?,?,?,?,?,?)";
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

	public void updateCliente(Cliente cliente) {
		String sql = "update  clientes  set  nome = ?, CPF = ?, email = ?, endereco = ?, dtNascimento = ? where codCliente = ? ";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCPF());
			pstm.setString(3, cliente.getEmail());
			pstm.setString(4, cliente.getEndereco());
			pstm.setString(5, cliente.getDtNascimento());
			pstm.setInt(6, cliente.getCodCliente());
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

	public ArrayList<Cliente> listaClientes() {

		String sql = "Select * from clientes order by codCliente";
		PreparedStatement pstm = null;
		Connection conn = null;
		ArrayList<Cliente> cliente = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			if (rs != null) {
				cliente = new ArrayList<Cliente>();
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.setCodCliente(rs.getInt("codCliente"));
					cli.setNome(rs.getString("nome"));
					cli.setCPF(rs.getString("CPF"));
					cli.setEmail(rs.getString("email"));
					cli.setEndereco(rs.getString("endereco"));
					cli.setDtNascimento(rs.getString("dtNascimento"));
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

	public void apagaCliente(Cliente cliente) {
		String sql = "Delete from clientes where codCliente = ?";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {

			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cliente.getCodCliente());
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
	public ArrayList<Cliente> listaClientesNome(String nome ) {

		String sql = "Select * from clientes WHERE nome LIKE '%" + nome + "%' ORDER BY codCliente";
		PreparedStatement pstm = null;
		Connection conn = null;
		ArrayList<Cliente> cliente = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			if (rs != null) {
				cliente = new ArrayList<Cliente>();
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.setCodCliente(rs.getInt("codCliente"));
					cli.setNome(rs.getString("nome"));
					cli.setCPF(rs.getString("CPF"));
					cli.setEmail(rs.getString("email"));
					cli.setEndereco(rs.getString("endereco"));
					cli.setDtNascimento(rs.getString("dtNascimento"));
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
	public void AlteraCpf(Cliente clie) {

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "UPDATE clientes set CPF = ?" ;
		Cliente cli = new Cliente();
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cli.getCPF());
			pstm.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if (pstm!= null ) {
					pstm.close();
				}
			}catch (SQLException ex ) {
				ex.printStackTrace();
			}try {
				if (conn != null) {
					conn.close();
				}
			}catch (SQLException ex ) {
				ex.printStackTrace();
			}
		}

	}
	

}
