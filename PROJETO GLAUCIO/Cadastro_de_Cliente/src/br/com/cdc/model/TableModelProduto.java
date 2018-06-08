package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelProduto extends AbstractTableModel{
	
	private List<Produto> produto;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelProduto(List<Produto> produto) {
		
		this.produto = new ArrayList<Produto>(produto);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return produto.size();
    }
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 3;
    }
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Id", "Nome","Valor"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	Produto produtos = produto.get(row);
        if (column == 0) return produtos.getId();
        else if (column == 1) return produtos.getNome();
        else return produtos.getValor();

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o Estado referente a linha especificada.
    	Produto est = produto.get(rowIndex);
     
    	if (columnIndex == 0) est.setId((Long)aValue);
        else if (columnIndex == 1) est.setNome((String)aValue);
        else if (columnIndex == 2) est.setValor((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o Estado referente a linha especificada
    public Produto getoduto(int indiceLinha) {
        return produto.get(indiceLinha);
    }
     
    // Adiciona o Estado especificado ao modelo
    public void addProduto(Produto produtos) {
        // Adiciona o registro.
    	produto.add(produtos);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o Estado da linha especificada.
    public void removeProduto(int indiceLinha) {
        // Remove o registro.
    	produto.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de estados no final da lista.
    public void addListaDeProdutos(List<Produto> produtoMethod) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        produto.addAll(produtoMethod);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + produto.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de Estados.
    	produto.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
