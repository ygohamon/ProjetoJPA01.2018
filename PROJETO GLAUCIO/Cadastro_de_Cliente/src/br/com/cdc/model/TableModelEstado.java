package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelEstado extends AbstractTableModel{
	
	private List<Estado> estados;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelEstado(List<Estado> estados) {
		
		this.estados = new ArrayList<Estado>(estados);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return estados.size();
    }
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 2;
    }
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Estado"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	Estado estado = estados.get(row);
        if (column == 0) return estado.getCodigo();
        else return estado.getNome();

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o Estado referente a linha especificada.
    	Estado est = estados.get(rowIndex);
     
    	if (columnIndex == 0) est.setCodigo((Long)aValue);
        else if (columnIndex == 1) est.setNome((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o Estado referente a linha especificada
    public Estado getEstado(int indiceLinha) {
        return estados.get(indiceLinha);
    }
     
    // Adiciona o Estado especificado ao modelo
    public void addEstado(Estado estado) {
        // Adiciona o registro.
    	estados.add(estado);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o Estado da linha especificada.
    public void removeEstado(int indiceLinha) {
        // Remove o registro.
    	estados.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de estados no final da lista.
    public void addListaDeEstados(List<Estado> estadosMethod) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        estados.addAll(estadosMethod);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + estados.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de Estados.
    	estados.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
