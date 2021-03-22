package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import loja.entidades.Cliente;

public class TableModel extends AbstractTableModel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private ArrayList<?> linhas = null;
	private List<Cliente> dados = new ArrayList<>();
	private String[] colunas = { "Codigo", "Nome", "CPF", "Email", "Endereço", "DTNasc" };
	private final int COLUNA_CODIGO = 0;
	private final int COLUNA_NOME = 1;
	private final int COLUNA_CPF = 2;
	private final int COLUNA_EMAIL = 3;
	private final int COLUNA_ENDERECO = 4;
	private final int COLUNA_DTNASC = 5;

	public TableModel(ArrayList<Cliente> cliente) {
		// TODO Auto-generated constructor stub
	}

	// public TableModel() {
	// TODO Auto-generated constructor stub
	// }
	public TableModel(ArrayList<?> linhas, String[] col) {
		setLinhas(linhas);
		setColunas(col);

	}

	public TableModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	public int getRowCount1() {
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {
		case 0:
			return dados.get(linha).getCodCliente();
		case 1:
			return dados.get(linha).getNome();
		case 2:
			return dados.get(linha).getCPF();
		case 3:
			return dados.get(linha).getEmail();
		case 4:
			return dados.get(linha).getEndereco();
		case 5:
			return dados.get(linha).getDtNascimento();
		}

		return null;

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// o argumento recebido pelo método é do tipo Object
		// mas como nossa tabela é de funcionários, é seguro(e até recomendável) fazer o
		// cast de suas propriedades
		Cliente cliente = this.dados.get(rowIndex);
		// de acordo com a coluna, ele preenche a célula com o valor
		// respectivo do objeto de mesmo indice na lista
		switch (columnIndex) {
		case COLUNA_CODIGO:
			/*
			 * cliente.setCodCliente(String.valueOf(aValue)); break;
			 */
		case COLUNA_NOME:
			cliente.setNome(String.valueOf(aValue));
			break;
		case COLUNA_CPF:
			cliente.setCPF(String.valueOf(aValue));
			break;
		case COLUNA_EMAIL:
			cliente.setEmail(String.valueOf(aValue));
		case COLUNA_ENDERECO:
			cliente.setEndereco(String.valueOf(aValue));
			break;
		case COLUNA_DTNASC:
			cliente.setDtNascimento(String.valueOf(aValue));
			break;
		}
		// este método é que notifica a tabela que houve alteração de dados
		fireTableDataChanged();
	}

	public ArrayList<?> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<?> linhas) {
		this.linhas = linhas;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public void addRow(Cliente c) {
		this.dados.add(c);
		this.fireTableDataChanged();
	}

	public void removeRow(int linha) {
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

	public Object getValue(int numLinhas, int numCol) {
		Object[] linhas = (Object[]) getLinhas().get(numLinhas);
		return linhas[numCol];
	}
	

}