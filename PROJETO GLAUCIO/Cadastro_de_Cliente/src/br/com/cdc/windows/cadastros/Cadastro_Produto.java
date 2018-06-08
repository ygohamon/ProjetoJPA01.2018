package br.com.cdc.windows.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import br.com.cdc.connection.Conexao;
import br.com.cdc.model.Produto;
import br.com.cdc.model.TableModelProduto;
import br.com.cdc.model.Tipo_Produto;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

public class Cadastro_Produto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textProduto;
	private JTable tableProdutos;
	private JTextField textValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Cadastro_Produto dialog = new Cadastro_Produto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setEnabled(true);
			dialog.setTitle("Cadastro de Produto");
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Cadastro_Produto() {
		
		setBounds(100, 100, 780, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCadastroDeProduto = DefaultComponentFactory.getInstance().createTitle("Cadastro de Produto");
		lblCadastroDeProduto.setBounds(5, 5, 422, 58);
		lblCadastroDeProduto.setFont(new Font("Dialog", Font.BOLD, 40));
		
		
		
		List<Tipo_Produto> tprodutosCombo = Conexao.listarTipo_Produto();
		
		String tprodutos[] = new String[tprodutosCombo.size()];
		
		for(int i = 0; i < tprodutosCombo.size(); i++) {
			
			Tipo_Produto tproduto = new Tipo_Produto();
			tprodutos[i] = tprodutosCombo.get(i).getNome();
		}
		
		textProduto = new JTextField();
		textProduto.setBounds(91, 209, 290, 33);
		textProduto.setFont(new Font("Dialog", Font.PLAIN, 16));
		textProduto.setColumns(10);
		
		textValor = new JTextField();
		textValor.setBounds(205, 294, 176, 33);
		textValor.setFont(new Font("Dialog", Font.PLAIN, 16));
		textValor.setColumns(10);
		
		JLabel lblNome = new JLabel("Produto: ");
		lblNome.setBounds(5, 215, 68, 21);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblTipoDeProduto = new JLabel("Tipo: ");
		lblTipoDeProduto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTipoDeProduto.setBounds(5, 256, 68, 21);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Dialog", Font.BOLD, 17));
		lblValor.setBounds(137, 300, 61, 14);
		
		
		JComboBox comboBoxProduto = new JComboBox(tprodutos);
		comboBoxProduto.setBounds(93, 252, 290, 31);
		comboBoxProduto.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		contentPanel.add(lblCadastroDeProduto);
		contentPanel.add(lblNome);
		contentPanel.add(lblTipoDeProduto);
		contentPanel.add(lblValor);
		contentPanel.add(textProduto);
		contentPanel.add(comboBoxProduto);
		contentPanel.add(textValor);
		
		JLabel lblProdutosCadastrados = new JLabel("Produtos Cadastrados");
		lblProdutosCadastrados.setFont(new Font("Dialog", Font.BOLD, 25));
		lblProdutosCadastrados.setBounds(461, 47, 269, 21);
		contentPanel.add(lblProdutosCadastrados);
		
		List<Produto> produtos = Conexao.listarProduto();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < produtos.size(); i++) {
			
			Produto produto = new Produto();
			produto.setId(produtos.get(i).getId());
			produto.setNome(produtos.get(i).getNome());
			produto.setValor(produtos.get(i).getValor());
			dados.add(produto);
		}
		
		TableModelProduto modelo = new TableModelProduto(dados);
		
		tableProdutos = new JTable();
		tableProdutos.setBounds(610, 80, 148, 355);
		contentPanel.add(tableProdutos);
		
		tableProdutos.setModel(modelo);
		tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableProdutos.getColumnModel().getColumn(0).setResizable(false);
		tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(210);
		tableProdutos.getColumnModel().getColumn(1).setResizable(false);

		JScrollPane scrollPane = new JScrollPane(tableProdutos);
		scrollPane.setBounds(461, 78, 297, 357);
		contentPanel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto produto = new Produto();
				
				produto.setNome(textProduto.getText());
				produto.setValor(textValor.getText());
				
				try {
					Conexao.guardar(produto);
					
				}catch(NullPointerException f) {
					
					JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
				}
				catch(Exception npe){
					
					JOptionPane.showMessageDialog(null, "Ops.. Algo deu errado: \n" +npe);
				}
				
				textProduto.setText("");
				textValor.setText("");
				JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
				modelo.addProduto(produto);
				tableProdutos.getModel();
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(91, 367, 90, 28);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textProduto.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_1.setBounds(205, 367, 90, 28);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
         	    Cadastro_Produto.this.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_2.setBounds(315, 367, 90, 28);
		contentPanel.add(btnNewButton_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(SystemColor.activeCaption);
		menuBar.setBackground(SystemColor.activeCaption);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnCadastrarTP = new JMenu("CADASTRAR");
		mnCadastrarTP.setForeground(Color.BLACK);
		mnCadastrarTP.setFont(new Font("Dialog", Font.BOLD, 25));
		menuBar.add(mnCadastrarTP);
		
		JMenuItem mntmCadastrarTipo = new JMenuItem("Cadastrar Tipo");
		mntmCadastrarTipo.setHorizontalAlignment(SwingConstants.CENTER);
		mntmCadastrarTipo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		mntmCadastrarTipo.addMouseListener(new MouseListener() {
			 
			@Override
	           public void mouseReleased(MouseEvent e) {   
	           }
	           @Override
	           public void mousePressed(MouseEvent e) { 
	        	   Cadastrar_TipoP frame = new Cadastrar_TipoP();
	        	   frame.setVisible(true);
	        	   frame.setResizable(false);
	           }
	           @Override
	           public void mouseExited(MouseEvent e) {   
	           }    
	           @Override
	           public void mouseEntered(MouseEvent e) {
	           }
	           @Override
	           public void mouseClicked(MouseEvent e) {
	           }
			
		});
		mnCadastrarTP.add(mntmCadastrarTipo);

		
		
		
		
	}
}
