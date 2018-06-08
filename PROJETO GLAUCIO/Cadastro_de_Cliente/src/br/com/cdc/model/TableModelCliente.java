package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelCliente extends AbstractTableModel{
	
	private List<Cliente> clientes;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelCliente(List<Cliente> clientes) {
		
		this.clientes = new ArrayList<Cliente>(clientes);
	}
	
	public int getRowCount() {

        return clientes.size();
    }
    public int getColumnCount() {

        return 2;
    }
    public String getColumnName(int columnIndex) {

        String colunas[] = {"Codigo", "Nome"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {

    	Cliente cliente = clientes.get(row);
        if (column == 0) return cliente.getCodigo();
        else return cliente.getNome();
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    	Cliente cli = clientes.get(rowIndex);
     
    	if (columnIndex == 0) cli.setCodigo((Long)aValue);
        else if (columnIndex == 1) cli.setNome((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Cliente getCliente(int indiceLinha) {
        return clientes.get(indiceLinha);
    }
     
    public void addCliente(Cliente cliente) {

    	clientes.add(cliente);
     
        int ultimoIndice = getRowCount() - 1;
     
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    public void removeCliente(int indiceLinha) {

    	clientes.remove(indiceLinha);
     
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    public void addListaDeClientes(List<Cliente> clientesMethod) {

        int indice = getRowCount();
     
        clientes.addAll(clientesMethod);
     
        fireTableRowsInserted(indice, indice + clientes.size());
    }
     
    public void limpar() {

    	clientes.clear();
     
        fireTableDataChanged();
    }
}
