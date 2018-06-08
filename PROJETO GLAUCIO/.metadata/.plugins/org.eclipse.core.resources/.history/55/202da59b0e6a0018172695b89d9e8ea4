package br.com.cdc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelVenda extends AbstractTableModel{
	
	private List<Venda> venda;
	@SuppressWarnings("unused")
	private String[] colunas;
	
	public TableModelVenda(List<Venda> vendas) {
		
		this.venda = new ArrayList<Venda>(venda);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return venda.size();
    }
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 2;
    }
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Id", "Produto"};
        return colunas[columnIndex];
    }
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	Venda vendas = venda.get(row);
        if (column == 0) return vendas.getId();
        else return vendas.getProduto();

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o Estado referente a linha especificada.
    	Venda est = venda.get(rowIndex);
     
    	if (columnIndex == 0) est.setId((Long)aValue);
        else if (columnIndex == 1) est.setProduto((Produto)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o Estado referente a linha especificada
    public Venda getoduto(int indiceLinha) {
        return venda.get(indiceLinha);
    }
     
    // Adiciona o Estado especificado ao modelo
    public void addVenda(Venda vendas) {
        // Adiciona o registro.
    	venda.add(vendas);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o Estado da linha especificada.
    public void removeVenda(int indiceLinha) {
        // Remove o registro.
    	venda.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de estados no final da lista.
    public void addListaDeVenda(List<Venda> vendaMethod) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        venda.addAll(vendaMethod);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + venda.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de Estados.
    	venda.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
