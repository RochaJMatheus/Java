package LojaViews;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.ini4j.InvalidFileFormatException;

import Controller.DepartamentoController;
import ExceptionDAO.ExceptioDao;
import Metodos.PropriedadesIni;
import Relatorios.Impressao;
import loja.entidades.Departamento;
import loja.entidades.Funcionarios;

public class Consulta extends PropriedadesIni {

	private JFrame frame;
	private JTextField edtNome;
	private JButton btnLimpar;
	private JTable tableMaster;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JButton btnVoltar;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta window = new Consulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws InvalidFileFormatException
	 */

	public Consulta() throws InvalidFileFormatException, IOException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws InvalidFileFormatException
	 * 
	 */
	private void initialize() throws InvalidFileFormatException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Pesquise por nome do Departamento");
		lblNewLabel.setBounds(105, 37, 210, 14);
		frame.getContentPane().add(lblNewLabel);

		edtNome = new JTextField();
		edtNome.setBounds(326, 34, 270, 20);
		frame.getContentPane().add(edtNome);

		edtNome.setColumns(10);
		try {
			PropriedadesIni prop = new PropriedadesIni();
			String nome = prop.leIni("C:\\Locadora\\Loja\\src\\prop.INI.txt", "key", "1");
			edtNome.setText((String) nome);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		frame.getContentPane();

		JButton btnGerarelatorio = new JButton("Relatorio");
		btnGerarelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Thread teste1 = new Thread(new Impressao(edtNome));

				try {
					teste1.join();
					teste1.start();

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}

		});
		btnGerarelatorio.setBounds(91, 290, 90, 23);
		frame.getContentPane().add(btnGerarelatorio);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				DefaultTableModel tableMaste = (DefaultTableModel) tableMaster.getModel();
				String[] opcoes = { "Tabela", "Campos", "Todos" };
				int a = JOptionPane.showOptionDialog(null, "Selecione que deseja  Limpar", "Limpar",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

				if (a == 0) {
					tableModel.setNumRows(0);
					tableMaste.setNumRows(0);
				} else if (a == 1) {
					edtNome.setText("");
				} else if (a == 2) {
					tableModel.setNumRows(0);
					tableMaste.setNumRows(0);
					edtNome.setText("");
				}
			}
		});
		btnLimpar.setBounds(570, 290, 89, 23);
		frame.getContentPane().add(btnLimpar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(113, 101, 518, 158);
		frame.getContentPane().add(scrollPane);

		tableMaster = new JTable();
		tableMaster.setFillsViewportHeight(true);
		tableMaster.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					String[] opcoes = { "Alterar", "Consultar" };
					int a = JOptionPane.showOptionDialog(null, "Selecione sua ação", "AÇÃO", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);

					if (a == 1) {

						DefaultTableModel tabelaModelo = (DefaultTableModel) table.getModel();

						int row = tableMaster.getSelectedRow();

						System.out.println(row);

						System.out.println("-----");

						int id = (int) tableMaster.getValueAt(row, 0);
						Controller.FuncionariosController funCon = new Controller.FuncionariosController();
						try {

							ArrayList<Funcionarios> funcionario = funCon.listafuncionarios(id);
							funcionario.forEach((Funcionarios funcionarios) -> {
								tabelaModelo.addRow(new Object[] { funcionarios.getID_funcionarios(),
										funcionarios.getNome(), funcionarios.getIdade(), funcionarios.getSalario(),
										funcionarios.getId_Departamento() });
							});

						} catch (ExceptioDao ex) {
							ex.printStackTrace();
						}
					} else if (a == 0) {

						Integer ID_DEP = (Integer) tableMaster.getModel().getValueAt(tableMaster.getSelectedRow(), 0);
						String nome = (String) tableMaster.getModel().getValueAt(tableMaster.getSelectedRow(), 1);

						CadastroDepartamento tela = new CadastroDepartamento();
						tela.AbreTela();
						tela.buscaDepartamento(ID_DEP, nome);

						frame.dispose();
					}
				}
			}

		});
		tableMaster.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Departamento" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMaster.getColumnModel().getColumn(1).setPreferredWidth(125);
		scrollPane.setViewportView(tableMaster);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(113, 366, 518, 294);
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo ", "Nome", "Idade", "Salario", "Cod Departamento" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 2967788152036518343L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Double.class,
					Integer.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(122);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					Integer idFuncionarios = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
					String nome = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
					Integer idade = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 2);
					Double Salario = (Double) table.getModel().getValueAt(table.getSelectedRow(), 3);
					Integer idDepartamento = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 4);

					CadastroFuncionarios tela = new CadastroFuncionarios();
					tela.BuscaFuncionarios(idFuncionarios, nome, idade, Salario, idDepartamento);
					tela.AbreTela(true);
					frame.dispose();

				}

			}
		});
		scrollPane_1.setViewportView(table);

		btnVoltar = new JButton("");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				LojaPrincipal tela = new LojaPrincipal();
				tela.abreTela(true);
				frame.dispose();
			}
		});
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\seta (1).png"));
		btnVoltar.setBounds(23, 28, 41, 23);
		frame.getContentPane().add(btnVoltar);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\papel-com-texto-e-lupa (1).png"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {
				String nome = edtNome.getText();
				PropriedadesIni ini = new PropriedadesIni();

				try {

					ini.escreveini("C:\\Locadora\\Loja\\src\\prop.INI.txt", "key", nome, "1");

					System.out.println(ini.leIni("C:\\Locadora\\Loja\\src\\prop.INI.txt", "key", "1"));
				} catch (InvalidFileFormatException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				DefaultTableModel tableModel = (DefaultTableModel) tableMaster.getModel();
				tableModel.setRowCount(0);

				DepartamentoController DpController = new DepartamentoController();

				try {
					ArrayList<Departamento> departamentos = DpController.listarDepartamentos(nome);
					departamentos.forEach((Departamento departamento) -> {
						tableModel.addRow(new Object[] { departamento.getID_departamento(), departamento.getNome() });

					});
				} catch (ExceptioDao ex) {
					ex.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(629, 20, 48, 31);
		frame.getContentPane().add(btnNewButton);

	}

	public void abreTela(boolean abre) {
		frame.setVisible(abre);
	}

	public void setanome(String nome) {
		edtNome.setText(nome);

	}
}
