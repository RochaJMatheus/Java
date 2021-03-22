package LojaViews;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.ini4j.InvalidFileFormatException;

public class LojaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LojaPrincipal window = new LojaPrincipal();
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
	public LojaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 530, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Departamento");
		menuBar.add(mnNewMenu);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroDepartamento tela = new CadastroDepartamento();
				tela.AbreTela();
				frame.dispose();
			}
		});
		mnNewMenu.add(btnNewButton);

		JMenu mnNewMenu_1 = new JMenu("Funcionarios");
		menuBar.add(mnNewMenu_1);

		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroFuncionarios tela = new CadastroFuncionarios();
				tela.AbreTela(true);
				frame.dispose();
			}
		});
		mnNewMenu_1.add(btnNewButton_1);

		JMenu mnNewMenu_2 = new JMenu("Consulta");
		menuBar.add(mnNewMenu_2);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Consulta tela = null;
				try {
					tela = new Consulta();
				} catch (InvalidFileFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tela.abreTela(true);
				frame.dispose();
			}
		});
		mnNewMenu_2.add(btnConsultar);

		JMenu mnNewMenu_2_1 = new JMenu("Clientes");
		menuBar.add(mnNewMenu_2_1);

		JButton btnConsu_cad = new JButton("Cad/Cons");
		btnConsu_cad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CadClientes tela = null;
				try {
					tela = new CadClientes();
				} catch (InvalidFileFormatException ex) {
			
					ex.printStackTrace();
				} catch (IOException ex) {
					
					ex.printStackTrace();
				}
				tela.abreTela(true);
				frame.dispose();

			}
		});
		mnNewMenu_2_1.add(btnConsu_cad);
	}

	public void abreTela(boolean Abret) {
		if (Abret == true) {
			frame.setVisible(Abret);
		}
	}
}
