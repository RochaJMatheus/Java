package LojaViews;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.ini4j.InvalidFileFormatException;

import Controller.DepartamentoController;
import Controller.FuncionariosController;
import ExceptionDAO.ExceptioDao;
import Metodos.ValidaCampos;
import loja.entidades.Departamento;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class CadastroFuncionarios {
	private Integer Id_funcionarios;
	private JFrame frame;
	private JTextField edtNome;
	private JTextField EdtIdade;
	private JTextField EdtSalario;
	private JTextField edtIdDepartamento;
	private JButton btnAlterar;
	private JButton btnNewButton_2;
	private JButton btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionarios window = new CadastroFuncionarios();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void BuscaFuncionarios(Integer ID_funcionarios, String nome, int idade, double salario,
			int Id_departamento) {
		this.Id_funcionarios = ID_funcionarios;
		this.edtNome.setText(nome);
		this.EdtIdade.setText(Integer.toString(idade));
		this.EdtSalario.setText(Double.toString(salario));
		this.edtIdDepartamento.setText(Integer.toString(Id_departamento));

	}

	/**
	 * Create the application.
	 */
	public CadastroFuncionarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ValidaCampos val = new ValidaCampos();
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 78, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		edtNome = new JTextField();
		edtNome.setBounds(76, 75, 293, 20);
		frame.getContentPane().add(edtNome);
		edtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Idade");
		lblNewLabel_1.setBounds(10, 128, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		EdtIdade = new JTextField();
		EdtIdade.setBounds(66, 125, 92, 20);
		frame.getContentPane().add(EdtIdade);
		EdtIdade.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Salario");
		lblNewLabel_2.setBounds(10, 197, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);

		EdtSalario = new JTextField();
		EdtSalario.setBounds(66, 194, 92, 20);
		frame.getContentPane().add(EdtSalario);
		EdtSalario.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("ID_Departamento");
		lblNewLabel_3.setBounds(10, 262, 89, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean sucesso;

				if (edtNome.getText().equals("") || EdtIdade.getText().equals("") || EdtSalario.getText().equals("")
						|| edtIdDepartamento.equals("")) {
					JOptionPane.showMessageDialog(null, "Preecha todos os campos para prosseguir!!! ");
				} else {

					try {
						Controller.FuncionariosController funcionariosController = new Controller.FuncionariosController();
						String nome = (String) edtNome.getText();
						Integer idade = Integer.parseInt(EdtIdade.getText().trim());
						Double salario = Double.parseDouble(EdtSalario.getText().trim());
						Integer id_departamento = Integer.parseInt(edtIdDepartamento.getText().trim());
						if (sucesso = true) {

							funcionariosController.insereFuncionarios(nome, idade, salario, id_departamento);

							JOptionPane.showInternalMessageDialog(null, "Salvo com sucesso !!!");

							edtIdDepartamento.setText("");
							edtNome.setText("");
							EdtIdade.setText("");
							EdtSalario.setText("");

						} else {
							JOptionPane.showInternalMessageDialog(null, "Erro!!!");
						}

					} catch (ExceptioDao ex) {
						// JOptionPane.showInternalMessageDialog(null, "Essa pessoa ja existe ");
					}
				}

			}
		});
		btnNewButton.setBounds(10, 378, 89, 23);
		frame.getContentPane().add(btnNewButton);

		edtIdDepartamento = new JTextField();
		edtIdDepartamento.setBounds(110, 259, 35, 20);
		frame.getContentPane().add(edtIdDepartamento);
		edtIdDepartamento.setColumns(10);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean sucesso;
				if (edtNome.getText().equals("") || EdtIdade.getText().equals("") || EdtSalario.getText().equals("")
						|| edtIdDepartamento.equals("")) {
					JOptionPane.showMessageDialog(null, "Preecha todos os campos para prosseguir!!! ");
				} else {

					try {
						FuncionariosController funCon = new FuncionariosController();

						String nome = edtNome.getText();
						Integer idade = Integer.parseInt(EdtIdade.getText());
						Double salario = Double.parseDouble(EdtSalario.getText());
						Integer Id_dep = Integer.parseInt(edtIdDepartamento.getText());
						if (sucesso = true) {
							sucesso = funCon.alteraFuncionario(Id_funcionarios, nome, idade, salario, Id_dep);

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao alterar!!");

						}
						edtIdDepartamento.setText("");
						edtNome.setText("");
						EdtIdade.setText("");
						EdtSalario.setText("");

					} catch (ExceptioDao ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnAlterar.setBounds(10, 412, 89, 23);
		frame.getContentPane().add(btnAlterar);

		btnNewButton_2 = new JButton("Consulta");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Consulta con = null;
				try {
					con = new Consulta();
				} catch (InvalidFileFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.abreTela(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(124, 378, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\seta (1).png"));
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LojaPrincipal tela = new LojaPrincipal();
				tela.abreTela(true);
				frame.dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 51, 23);
		frame.getContentPane().add(btnVoltar);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean sucesso;
				FuncionariosController controller = new FuncionariosController();

				if (Id_funcionarios == null || Id_funcionarios == 0) {
					JOptionPane.showInternalMessageDialog(null, "Erro !!!");
				} else {

					try {

						if (sucesso = true) {
							controller.deletaFuncionarios(Id_funcionarios);
							JOptionPane.showMessageDialog(null, "Funcionario Deletado com sucesso !!! ");
						} else {
							JOptionPane.showInternalConfirmDialog(null, "Erro ao Deletar!!!");
						}
						edtIdDepartamento.setText("");
						edtNome.setText("");
						EdtIdade.setText("");
						EdtSalario.setText("");

					} catch (ExceptioDao ex) {
						ex.printStackTrace();
					}

				}

			}
		});
		btnApagar.setBounds(124, 412, 89, 23);
		frame.getContentPane().add(btnApagar);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		scrollPane.setBounds(492, 115, 405, 286);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Departamento" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(121);
		table.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {
				try {

					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					DepartamentoController controller = new DepartamentoController();
					try {

						ArrayList<Departamento> dep = controller.listaDep();
						dep.forEach((Departamento deps) -> {
							tableModel.addRow(new Object[] { deps.getID_departamento(), deps.getNome() });
						});
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(table);

		btnNewButton_1 = new JButton("limpar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				String[] opcoes = { "Tabela", "Campos", "Todos" };
				int a = JOptionPane.showOptionDialog(null, "Selecione que deseja  Limpar", "Limpar",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

				if (a == 0) {
					tableModel.setNumRows(0);

				} else if (a == 1) {
					edtIdDepartamento.setText("");
					edtNome.setText("");
					EdtIdade.setText("");
					EdtSalario.setText("");

				} else if (a == 2) {
					tableModel.setNumRows(0);
					edtIdDepartamento.setText("");
					edtNome.setText("");
					EdtIdade.setText("");
					EdtSalario.setText("");
				}
			}
		});
		btnNewButton_1.setBounds(248, 400, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

	}

	public void AbreTela(boolean abreT) {
		if (abreT == true) {
			frame.setVisible(abreT);
		}

	}

	public static String formataDados(String dado) {
		return dado = dado.replaceAll(",", ".");
	}
}
