package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionDao.Conexao;
import ExceptionDAO.ExceptioDao;
import loja.entidades.Departamento;

public class DepartamentoDao {

	public void insereDepartamento(Departamento departamento) throws ExceptioDao {

		String sql = "INSERT into departamento (nome) values (?)";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, departamento.getNome());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao inserir o departamneto" + ex);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar statement" + ex);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar conexão" + ex);
			}
		}
	}

	public void alteraDepartamento(Departamento departamento) throws ExceptioDao {
		String sql = "UPDATE departamento set  nome = ? where ID_departamento = ?";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, departamento.getNome());
			pstm.setInt(2, departamento.getID_departamento());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao alterar !!!" + ex);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar o Statement" + ex);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("erro ao fechar connection " + ex);
			}
		}
	}

	public void apagaDepartamento(Departamento departamento) throws ExceptioDao {
		String sql = "Delete from  departamento where ID_departamento = ?";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, departamento.getID_departamento());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao deletar " + ex);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar o Statement" + ex);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("erro ao fechar connection " + ex);
			}
		}

	}

	public ArrayList<Departamento> listaDepartamentos(String nome) throws ExceptioDao {
		String slq = "Select * from departamento WHERE nome LIKE '%" + nome + "%' ORDER BY nome";
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<Departamento> departamentos = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(slq);
			ResultSet rs = pstm.executeQuery();

			if (rs != null) {
				departamentos = new ArrayList<Departamento>();
				while (rs.next()) {
					Departamento departamento = new Departamento();
					departamento.setID_departamento(rs.getInt("ID_departamento"));
					departamento.setNome(rs.getString("nome"));
					departamentos.add(departamento);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao listar Departamentos!!!" + ex);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar o statement " + ex);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar conexão.." + ex);
			}
		}
		return departamentos;
	}
	public ArrayList<Departamento> listaDep() {

		String sql = "select * from departamento";
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<Departamento> departamentos = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			if (rs != null) {
				departamentos = new ArrayList<Departamento>();
				while (rs.next()) {
					Departamento departamento = new Departamento();
					departamento.setID_departamento(rs.getInt("ID_departamento"));
					departamento.setNome(rs.getString("nome"));
					departamentos.add(departamento);
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

		return departamentos;

	}

}
