package Relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JTextField;

import ConnectionDao.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Impressao implements Runnable {

	private JTextField tfDepartamento;

	public Impressao(JTextField tfDepartamento) {
		this.tfDepartamento = tfDepartamento;
	}

	

	private void geraRelatorio() throws FileNotFoundException {

		Object obj = tfDepartamento.getText();

		String path = "C://Locadora//Loja//Relatorios//teste1.jasper";

		Connection conn = new Conexao().getConnection();

		try {

			HashMap<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("Departamento", obj.toString());

			JasperPrint print = JasperFillManager.fillReport("C://Locadora//Loja//Relatorios//teste1.jasper", parametro,
					conn);

			JasperViewer.viewReport(print, false);

			try {

				byte[] bytes = null;

				JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile(path);

				bytes = JasperRunManager.runReportToPdf(relatorio, parametro, conn);

				File arq = new File("C:\\teste\\relat.pdf");

				FileOutputStream fos = new FileOutputStream(arq);
				fos.write(bytes);
				fos.flush();
				fos.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (JRException ex) {
			System.out.println(ex.getMessage());
		}

	}



	@Override
	public void run() {
		try {
			geraRelatorio();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
