package LojaViews;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.ScrollPane;
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

import Controller.ClienteController;
import Metodos.LeCSV;
import Metodos.PropriedadesIni;
import ThreadImport.AtualizaCpf;
import ThreadImport.Importa;
import loja.entidades.Cliente;

public class CadClientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DesktopPane = null;
	private JFrame frame;
	private JTextField edtCodigo;
	private JTextField edtNome;
	private JTextField edtCPF;
	private JTextField edtEmail;
	private JTextField edtEndereco;
	private JTextField edtNascimento;
	private JButton btnConsultar;
	private JTable tabela;
	private ScrollPane scrollPane;
	private JTextField EdtPesNome;
	private JTable table;
	private Integer codCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CadClientes window = new CadClientes();
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
	public CadClientes() throws InvalidFileFormatException, IOException {
		initialize();

		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws InvalidFileFormatException
	 */
	private void initialize() throws InvalidFileFormatException, IOException {

		frame = new JFrame();
		frame.setBounds(100, 100, 1382, 731);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBounds(100, 100, 1360, 573);
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Codigo ");
		lblNewLabel.setBounds(10, 68, 93, 14);
		frame.getContentPane().add(lblNewLabel);

		edtCodigo = new JTextField();
		edtCodigo.setBounds(66, 65, 79, 20);
		frame.getContentPane().add(edtCodigo);
		edtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 115, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		edtNome = new JTextField();
		edtNome.setBounds(66, 112, 246, 20);
		frame.getContentPane().add(edtNome);
		edtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(10, 176, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);

		edtCPF = new JTextField();
		edtCPF.setBounds(66, 173, 220, 20);
		frame.getContentPane().add(edtCPF);
		edtCPF.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 240, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);

		edtEmail = new JTextField();
		edtEmail.setBounds(67, 237, 316, 20);
		frame.getContentPane().add(edtEmail);
		edtEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Endere\u00E7o");
		lblNewLabel_4.setBounds(10, 306, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);

		edtEndereco = new JTextField();
		edtEndereco.setBounds(67, 303, 316, 20);
		frame.getContentPane().add(edtEndereco);
		edtEndereco.setColumns(10);

		edtNascimento = new JTextField();
		edtNascimento.setBounds(109, 356, 86, 20);
		frame.getContentPane().add(edtNascimento);
		edtNascimento.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Dt/Nascimento");
		lblNewLabel_5.setBounds(10, 359, 93, 14);
		frame.getContentPane().add(lblNewLabel_5);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Integer cod = Integer.parseInt(edtCodigo.getText());
				if (edtCodigo.getText().equals("") || edtCPF.getText().equals("") || edtNome.getText().equals("")
						|| edtEmail.getText().equals("") || edtEndereco.getText().equals("")
						|| edtNascimento.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Preencha todos os campos para prosseguir !!!");
				} else {

					try {
						ClienteController controller = new ClienteController();
						controller.AlteraClientes(cod, edtNome.getText(), edtCPF.getText(), edtEmail.getText(),
								edtEndereco.getText(), edtNascimento.getText());
						JOptionPane.showMessageDialog(null, "alterado com sucesso ");

						edtNome.setText("");
						edtCodigo.setText("");
						edtCPF.setText("");
						edtEmail.setText("");
						edtEndereco.setText("");
						edtNascimento.setText("");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnAlterar.setBounds(14, 527, 89, 42);
		frame.getContentPane().add(btnAlterar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(14, 453, 89, 42);

		btnSalvar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				String pesquisa = EdtPesNome.getText();

				Integer codigo = Integer.parseInt(edtCodigo.getText().trim());
				String nome = edtNome.getText().trim();
				String CPF = edtCPF.getText().trim();
				String email = edtEmail.getText().trim();
				String endereco = edtEndereco.getText().trim();
				String dtNascimento = edtNascimento.getText().trim();

				boolean sucesso;

				if (edtCodigo.getText().equals("") || edtCPF.getText().equals("") || edtNome.getText().equals("")
						|| edtEmail.getText().equals("") || edtEndereco.getText().equals("")
						|| edtNascimento.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Preencha todos os campos para prosseguir !!!");
				} else {

					try {

						ClienteController controller = new ClienteController();

						if (sucesso = true) {
							controller.InsereClientes(codigo, nome, CPF, email, endereco, dtNascimento);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso !!!");

							edtCodigo.setText("");
							edtNome.setText("");
							edtCPF.setText("");
							edtEmail.setText("");
							edtEndereco.setText("");
							edtNascimento.setText("");

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao salvar!!!");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});
		frame.getContentPane().add(btnSalvar);
		// frame.getContentPane().add(table);

		// scrollPane_1.setBounds(497, 65, 745, 459);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\papel-com-texto-e-lupa (1).png"));
		btnNewButton.setBounds(1099, 54, 89, 42);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);

				ClienteController Controller = new ClienteController();
				String nome = EdtPesNome.getText();
				try {
					PropriedadesIni ini = new PropriedadesIni();
					ini.escreveini("C:\\Locadora\\Loja\\src\\clientesProp.INI.txt", "key", nome, "1");

					ArrayList<Cliente> cliente = Controller.listaClienteNome(nome);
					cliente.forEach((Cliente cli) -> {
						tableModel.addRow(new Object[] { cli.getCodCliente(), cli.getNome(), cli.getCPF(),
								cli.getEmail(), cli.getEndereco(), cli.getDtNascimento() });

					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});
		frame.getContentPane().add(btnNewButton);

		JButton btnImportar = new JButton("Importar");
		btnImportar.setBounds(260, 453, 89, 42);
		btnImportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Carregamentos do processo de "Import" das pessoas
				Thread teste = new Thread(new Importa());

				try {

					teste.join();
					teste.start();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnImportar);

		EdtPesNome = new JTextField();
		EdtPesNome.setBounds(604, 65, 417, 20);
		frame.getContentPane().add(EdtPesNome);
		EdtPesNome.setColumns(10);
		try {
			PropriedadesIni in = new PropriedadesIni();
			String leIni = in.leIni("C:\\Locadora\\Loja\\src\\clientesProp.INI.txt", "key", "1");
			EdtPesNome.setText(leIni);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JLabel lblNewLabel_6 = new JLabel("Nome ");
		lblNewLabel_6.setBounds(515, 68, 79, 14);
		frame.getContentPane().add(lblNewLabel_6);

		JButton btnLimpar_edt = new JButton("Limpar");
		btnLimpar_edt.setBounds(135, 453, 89, 42);
		btnLimpar_edt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				String[] opcoes = { "Tabela", "Campos", "Todos" };
				int a = JOptionPane.showOptionDialog(null, "Selecione que deseja  Limpar", "Limpar",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

				if (a == 0) {
					tableModel.setNumRows(0);

				} else if (a == 1) {

					edtNome.setText("");
					edtCodigo.setText(" ");
					edtCPF.setText("");
					edtEmail.setText("");
					edtEndereco.setText("");
					edtNascimento.setText("");
					EdtPesNome.setText("");
				} else if (a == 2) {
					tableModel.setNumRows(0);
					edtNome.setText("");
					edtCodigo.setText(" ");
					edtCPF.setText("");
					edtEmail.setText("");
					edtEndereco.setText("");
					edtNascimento.setText("");
					EdtPesNome.setText("");
				}
			}

		});
		frame.getContentPane().add(btnLimpar_edt);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {

		});
		scrollPane_1.setBounds(503, 162, 834, 519);
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					Integer codCliente = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 0);
					String nome = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
					String CPF = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
					String email = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
					String endereco = (String) table.getModel().getValueAt(table.getSelectedRow(), 4);
					String dtNascimento = (String) table.getModel().getValueAt(table.getSelectedRow(), 5);

					buscaCampos(codCliente, nome, CPF, email, endereco, dtNascimento);

				}

			}

		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Nome", "CPF", "Email", "Endere\u00E7o", "DT Nasc" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				LojaPrincipal tela = new LojaPrincipal();
				tela.abreTela(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\CHIPTRONIC-324\\Downloads\\seta (1).png"));
		btnNewButton_1.setBounds(14, 11, 58, 28);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean sucesso;

				ClienteController controller = new ClienteController();
				int cod = Integer.parseInt(edtCodigo.getText());
				if (cod == 0) {
					JOptionPane.showInternalMessageDialog(null, "Erro ");
				} else {

					if (sucesso = true) {

						try {
							controller.deletaCliente(cod);

							edtNome.setText("");
							edtCodigo.setText(" ");
							edtCPF.setText("");
							edtEmail.setText("");
							edtEndereco.setText("");
							edtNascimento.setText("");
							EdtPesNome.setText("");

						} catch (Exception ex) {
							ex.printStackTrace();
						}

					}

				}

			}
		});
		btnApagar.setBounds(135, 527, 89, 42);
		frame.getContentPane().add(btnApagar);

		JButton btnAlteraCpf = new JButton("AlterarCPF");
		btnAlteraCpf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Thread teste = new Thread(new AtualizaCpf());
				try {
					teste.join();
					teste.start();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnAlteraCpf.setBounds(260, 527, 89, 42);
		frame.getContentPane().add(btnAlteraCpf);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(230, 351, 388, -215);
		frame.getContentPane().add(scrollPane_2);

		btnConsultar = new JButton("Consultar");

	}

	public void abreTela(boolean tela) {
		if (tela == true) {
			frame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao abrir tela !!!");
		}
	}

	public void buscaCampos(Integer codCli, String nome, String CPF, String email, String endereco,
			String dtNascimento) {
		this.edtCodigo.setText(Integer.toString(codCli));
		this.edtNome.setText(nome);
		this.edtCPF.setText(CPF);
		this.edtEmail.setText(email);
		this.edtEndereco.setText(endereco);
		this.edtNascimento.setText(dtNascimento);
	}

}
