package br.com.cdc.windows.cadastros;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import br.com.cdc.connection.Conexao;
import br.com.cdc.model.Cliente;
import br.com.cdc.model.Produto;
import br.com.cdc.model.TableModelProduto;
import br.com.cdc.model.TableModelVenda;
import br.com.cdc.model.Venda;

public class Cadastro_Venda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableVendas;

	private JTextField textValorTotal;
	private JTextField textFieldQT;

	public static void main(String[] args) {
		try {
			Cadastro_Venda dialog = new Cadastro_Venda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setEnabled(true);
			dialog.setTitle("Cadastro de Venda");
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cadastro_Venda() {
		
		setBounds(100, 100, 780, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		List<Produto> produtoCombo = Conexao.listarProduto();
		
		String produtos[] = new String[produtoCombo.size()];
		
		for(int i = 0; i < produtoCombo.size(); i++) {
			
			Produto produto = new Produto();
			produtos[i] = produtoCombo.get(i).getNome();
		}
		
		List<Produto> valorCombo = Conexao.listarProduto();
		String valores[] = new String[valorCombo.size()];
		for(int i = 0; i < valorCombo.size(); i++) {
			
			Produto valor = new Produto();
			valores[i] = valorCombo.get(i).getValor();
		}
		
		
		List<Cliente> clienteCombo = Conexao.listarClientes();
		
		String clientes[] = new String[clienteCombo.size()];
		
		for(int i = 0; i < clienteCombo.size(); i++) {
			
			Cliente cliente = new Cliente();
			clientes[i] = clienteCombo.get(i).getNome();
		}
		
		JLabel lblCadastroDeVenda = DefaultComponentFactory.getInstance().createTitle("Cadastro de Venda");
		lblCadastroDeVenda.setBounds(5, 5, 422, 58);
		lblCadastroDeVenda.setFont(new Font("Dialog", Font.BOLD, 45));
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(11, 207, 64, 21);
		lblCliente.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(11, 257, 81, 21);
		lblProduto.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPanel.setLayout(null);
		
		JComboBox comboBoxCliente = new JComboBox(clientes);
		comboBoxCliente.setBounds(85, 207, 290, 31);
		comboBoxCliente.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		
		JComboBox comboBoxProduto = new JComboBox(produtos);
		comboBoxProduto.setBounds(85, 257, 290, 31);
		comboBoxProduto.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		
		textFieldQT = new JTextField();
		textFieldQT.setBounds(85, 311, 290, 31);
		textFieldQT.setColumns(10);
		
		
		contentPanel.add(lblCadastroDeVenda);
		contentPanel.add(lblCliente);
		contentPanel.add(lblProduto);
		contentPanel.add(comboBoxCliente);
		contentPanel.add(comboBoxProduto);
		contentPanel.add(textFieldQT);
		
		
		JLabel lblVendaCadastradas = new JLabel("Produtos da Venda");
		lblVendaCadastradas.setFont(new Font("Dialog", Font.BOLD, 25));
		lblVendaCadastradas.setBounds(460, 47, 265, 21);
		contentPanel.add(lblVendaCadastradas);
		
		//TESTE
		
		List<Venda> vendas = Conexao.listarVenda();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < vendas.size(); i++) {
			
			Venda venda = new Venda();
			venda.setId(vendas.get(i).getId());
			venda.setNumero(vendas.get(i).getNumero());
			venda.setCliente(vendas.get(i).getCliente());
			venda.setProduto(vendas.get(i).getProduto());
			dados.add(venda);
		}
		
		TableModelVenda modelo = new TableModelVenda(dados);
		
		tableVendas = new JTable();
		tableVendas.setShowVerticalLines(true);
		tableVendas.setShowHorizontalLines(true);
		tableVendas.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableVendas.setColumnSelectionAllowed(true);
		tableVendas.setBounds(1002, 100, 176, 505);
		
		tableVendas.setModel(modelo);
		tableVendas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableVendas.getColumnModel().getColumn(0).setResizable(false);
		tableVendas.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableVendas.getColumnModel().getColumn(1).setResizable(false);
		tableVendas.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableVendas.getColumnModel().getColumn(2).setResizable(false);
		tableVendas.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableVendas.getColumnModel().getColumn(3).setResizable(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
         	    Cadastro_Venda.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(293, 417, 90, 28);
		contentPanel.add(btnVoltar);
		
		textValorTotal = new JTextField();
		textValorTotal.setBounds(607, 403, 118, 42);
		contentPanel.add(textValorTotal);
		textValorTotal.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane(tableVendas);
		scrollPane.setBounds(385, 79, 340, 313);
		contentPanel.add(scrollPane);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Dialog", Font.BOLD, 25));
		lblValorTotal.setBounds(460, 406, 142, 32);
		contentPanel.add(lblValorTotal);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Venda venda = new Venda();
				
				venda.setNumero(textFieldQT.getText());
				venda.setProduto(comboBoxProduto.getSelectedItem().toString());
				venda.setCliente(comboBoxCliente.getSelectedItem().toString());
				
				try {
					
					Conexao.guardar(venda);
					
				}catch(NullPointerException f) {
					
					JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
				}
				catch(Exception npe){
					
					JOptionPane.showMessageDialog(null, "Ops.. Algo deu errado: \n" +npe);
				}
				textFieldQT.setText("");
				comboBoxProduto.setSelectedItem(produtos[0]);
				comboBoxCliente.setSelectedItem(clientes[0]);
				JOptionPane.showMessageDialog(null,"Venda Cadastrada!");
				modelo.addVenda(venda);
				tableVendas.getModel();
				
			}
		});
		btnGravar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnGravar.setBounds(193, 417, 90, 28);
		contentPanel.add(btnGravar);
		
		
		JLabel lblCodBarra = new JLabel("Quantia:");
		lblCodBarra.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCodBarra.setBounds(11, 311, 64, 31);
		contentPanel.add(lblCodBarra);
		
		
		
	
		
	}
}
