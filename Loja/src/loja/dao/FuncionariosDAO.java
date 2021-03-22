package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionDao.Conexao;
import ExceptionDAO.ExceptioDao;
import loja.entidades.Departamento;
import loja.entidades.Funcionarios;

public class FuncionariosDAO {

	public void insereFuncionario(Funcionarios funcionario) throws ExceptioDao {

		String sql = "INSERT INTO funcionarios (nome,idade,Salario,ID_departamento) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, funcionario.getNome());
			pstm.setInt(2, funcionario.getIdade());
			pstm.setDouble(3, funcionario.getSalario());
			pstm.setInt(4, funcionario.getId_Departamento());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao cadastrar !!!" + ex);
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

	public void alteraFuncionario(Funcionarios funcionario) throws ExceptioDao {
		String sql = "UPDATE funcionarios set nome = ?, idade = ?, Salario = ?, ID_departamento = ? where ID_funcionarios = ? ";
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, funcionario.getNome());
			pstm.setInt(2, funcionario.getIdade());
			pstm.setDouble(3, funcionario.getSalario());
			pstm.setInt(4, funcionario.getId_Departamento());
			pstm.setInt(5, funcionario.getID_funcionarios());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao atualizar os dados" + ex);
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

	public void deletafuncionarios(Funcionarios funcionario) throws ExceptioDao {
		String sql = "DELETE from funcionarios where ID_funcionarios = ?";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, funcionario.getID_funcionarios());
			pstm.execute();

		} catch (SQLException ex) {
			throw new ExceptioDao("erro ao efetuar deleção " + ex);
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
				throw new ExceptioDao("Erro ao fecahr conexão " + ex);
			}
		}
	}

	public ArrayList<Funcionarios> listaFuncionarios(int id) throws ExceptioDao {
		// String sql = "Select
		// funcionarios.ID_funcionarios,funcionarios.nome,funcionarios.idade,funcionarios.salario
		// ,funcionarios.ID_departamento from funcionarios, departamento where
		// funcionarios.ID_departamento = departamento.ID_departamento";
		String sql = "Select * from funcionarios WHERE ID_departamento LIKE '%" + id + "%' ORDER BY nome";

		Connection conn = null;
		PreparedStatement pstm = null;

		ArrayList<Funcionarios> funcionarios = null;
		try {
			conn = new Conexao().getConnection();
			// Funcionarios fun = new Funcionarios();
			pstm = conn.prepareStatement(sql);
			// pstm.setInt(1, fun.getID_funcionarios());
			ResultSet rs = pstm.executeQuery();

			if (rs != null) {
				funcionarios = new ArrayList<Funcionarios>();
				while (rs.next()) {
					Funcionarios funcionario = new Funcionarios();
					funcionario.setID_funcionarios(rs.getInt("ID_funcionarios"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setIdade(rs.getInt("idade"));
					funcionario.setSalario(rs.getDouble("Salario"));
					funcionario.setId_Departamento(rs.getInt("ID_departamento"));
					funcionarios.add(funcionario);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptioDao("Erro ao listar funcionarios" + ex.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("erro ao fechar Statement " + ex);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				throw new ExceptioDao("Erro ao fechar conexão" + ex);
			}
		}
		return funcionarios;

	}

	public ArrayList<Departamento> listaDep() {

		String sql = "select * from departamento;";
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
