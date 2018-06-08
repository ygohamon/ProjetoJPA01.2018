package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelTipo extends AbstractTableModel{
	
	private List<Tipo_Produto> tproduto;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelTipo(List<Tipo_Produto> tproduto) {
		
		this.tproduto = new ArrayList<Tipo_Produto>(tproduto);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return tproduto.size();
    }
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 2;
    }
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Id", "Nome"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	Tipo_Produto tprodutos = tproduto.get(row);
        if (column == 0) return tprodutos.getId();
        else return tprodutos.getNome();

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o Estado referente a linha especificada.
    	Tipo_Produto est = tproduto.get(rowIndex);
     
    	if (columnIndex == 0) est.setId((Long)aValue);
        else if (columnIndex == 1) est.setNome((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o Estado referente a linha especificada
    public Tipo_Produto getTproduto(int indiceLinha) {
        return tproduto.get(indiceLinha);
    }
     
    // Adiciona o Estado especificado ao modelo
    public void addTipo_Produto(Tipo_Produto tprodutos) {
        // Adiciona o registro.
    	tproduto.add(tprodutos);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o Estado da linha especificada.
    public void removeTipo_Produto(int indiceLinha) {
        // Remove o registro.
    	tproduto.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de estados no final da lista.
    public void addListaDeTipo_Produtos(List<Tipo_Produto> tprodutoMethod) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        tproduto.addAll(tprodutoMethod);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + tproduto.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de Estados.
    	tproduto.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
