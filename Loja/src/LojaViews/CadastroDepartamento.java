package LojaViews;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import Controller.DepartamentoController;
import ExceptionDAO.ExceptioDao;
import Metodos.ValidaCampos;
import loja.entidades.Departamento;

public class CadastroDepartamento {

	private JFrame frame;
	private JTextField edtNome;
	private Integer codDepartamento;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDepartamento window = new CadastroDepartamento();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public CadastroDepartamento() {
		initialize();

	}

	public void buscaDepartamento(Integer codDepartamento, String nome) {
		this.codDepartamento = codDepartamento;
		this.edtNome.setText(nome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ValidaCampos val = new ValidaCampos();
		frame = new JFrame();
		frame.setBounds(100, 100, 897, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(72, 60, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		edtNome = new JTextField();
		edtNome.setBounds(165, 57, 239, 20);
		frame.getContentPane().add(edtNome);
		edtNome.setColumns(10);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {
				boolean sucesso;
				String nome = edtNome.getText();

				if (val.validaCamposDep(nome)) {

				} else {

					Controller.DepartamentoController departamentoController = new Controller.DepartamentoController();
					if (sucesso = true) {

						try {
							departamentoController.inserirDepartamento(edtNome.getText());
							JOptionPane.showInternalMessageDialog(null, "Salvo com sucesso!!!");
							edtNome.setText("");
						} catch (ExceptioDao ex) {
							JOptionPane.showMessageDialog(null, "Erro ");
						}

					} else {
						JOptionPane.showInternalMessageDialog(null, "Erro ao salvar !!! ");

					}
				}
			}

		});
		btnNewButton.setBounds(78, 141, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\seta (1).png"));
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				LojaPrincipal tela = new LojaPrincipal();
				tela.abreTela(true);
				frame.dispose();

			}
		});
		btnVoltar.setBounds(10, 11, 58, 23);
		frame.getContentPane().add(btnVoltar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String nome = edtNome.getText();

				if (val.validaCamposDep(nome) == true && codDepartamento == null) {
				} else {

					boolean sucesso;

					DepartamentoController controller = new DepartamentoController();

					try {

						sucesso = controller.alteraDepartamento(codDepartamento, nome);
						JOptionPane.showMessageDialog(null, "Alterado com sucesso ");

						edtNome.setText("");

					} catch (ExceptioDao ex) {
						ex.printStackTrace();

					}
				}
			}
		});
		btnAlterar.setBounds(216, 141, 89, 23);
		frame.getContentPane().add(btnAlterar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
			}
		});
		scrollPane.setBounds(125, 240, 561, 209);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
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
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Departamento" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		scrollPane.setViewportView(table);

		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel tableMaste = (DefaultTableModel) table.getModel();
				String[] opcoes = { "Tabela", "Campos", "Todos" };
				int a = JOptionPane.showOptionDialog(null, "Selecione que deseja  Limpar", "Limpar",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

				if (a == 0) {

					tableMaste.setNumRows(0);
				} else if (a == 1) {
					edtNome.setText("");
				} else if (a == 2) {
					tableMaste.setNumRows(0);
					edtNome.setText("");
				}
			}

		});
		btnlimpar.setBounds(336, 141, 89, 23);
		frame.getContentPane().add(btnlimpar);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String nome = edtNome.getText();
				boolean sucesso;

				DepartamentoController controller = new DepartamentoController();

				if (codDepartamento == null) {
					JOptionPane.showMessageDialog(null, "Erro !!!");
				} else {

					if (sucesso = true) {

						try {
							controller.deletadepartamento(codDepartamento);

							edtNome.setText("");
						} catch (ExceptioDao e1) {

							e1.printStackTrace();
						}
					}

				}
			}
		});
		btnApagar.setBounds(464, 141, 89, 23);
		frame.getContentPane().add(btnApagar);
	}

	public void AbreTela() {
		frame.setVisible(true);
	}
}
