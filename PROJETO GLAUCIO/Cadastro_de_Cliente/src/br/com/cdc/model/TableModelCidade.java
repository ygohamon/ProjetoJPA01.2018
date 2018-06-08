package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelCidade extends AbstractTableModel{
	
	private List<Cidade> cidades;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelCidade(List<Cidade> cidades) {
		
		this.cidades = new ArrayList<Cidade>(cidades);
	}
	
	public int getRowCount() {

        return cidades.size();
    }
    public int getColumnCount() {

        return 2;
    }
    public String getColumnName(int columnIndex) {

        String colunas[] = {"Codigo", "Cidade"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {

    	Cidade cidade = cidades.get(row);
        if (column == 0) return cidade.getCodigo();
        else return cidade.getNomeCidade();
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    	Cidade cid = cidades.get(rowIndex);
     
    	if (columnIndex == 0) cid.setCodigo((Long)aValue);
        else if (columnIndex == 1) cid.setNomeCidade((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Cidade getCidade(int indiceLinha) {
    	
        return cidades.get(indiceLinha);
    }
     

    public void addCidade(Cidade cidade) {

    	cidades.add(cidade);
     
        int ultimoIndice = getRowCount() - 1;
     
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    public void removeCidade(int indiceLinha) {

    	cidades.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeCidades(List<Cidade> cidadesMethod) {

        int indice = getRowCount();
     
        cidades.addAll(cidadesMethod);
     
        fireTableRowsInserted(indice, indice + cidades.size());
    }
 
    public void limpar() {
    	
    	cidades.clear();
     
        fireTableDataChanged();
    }
}
