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
import br.com.cdc.model.Cidade;
import br.com.cdc.model.Estado;
import br.com.cdc.model.TableModelCidade;
import br.com.cdc.model.TableModelCliente;

public class Cadastro_Cidade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCidade;
	private JTable tableCidade;

	public static void main(String[] args) {
		try {
			Cadastro_Cidade dialog = new Cadastro_Cidade();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setEnabled(true);
			dialog.setTitle("Cadastro de Cidade");
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cadastro_Cidade() {
		
		setBounds(100, 100, 780, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		List<Estado> estadosCombo = Conexao.listarEstados();
		
		String estados[] = new String[estadosCombo.size()];
		
		for(int i = 0; i < estadosCombo.size(); i++) {
			
			Estado estado = new Estado();
			estados[i] = estadosCombo.get(i).getNome();
		}
		
		JLabel lblCadastroDeCidade = DefaultComponentFactory.getInstance().createTitle("Cadastro de Cidade");
		lblCadastroDeCidade.setBounds(5, 5, 422, 58);
		lblCadastroDeCidade.setFont(new Font("Dialog", Font.BOLD, 45));
		
		JLabel lblCidade = new JLabel("Cidade.:");
		lblCidade.setBounds(11, 207, 64, 21);
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblEstado = new JLabel("Estado.:");
		lblEstado.setBounds(11, 257, 64, 21);
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 16));
		
		textCidade = new JTextField();
		textCidade.setBounds(93, 201, 290, 33);
		textCidade.setFont(new Font("Dialog", Font.PLAIN, 16));
		textCidade.setColumns(10);
		
		JComboBox comboBoxEstado = new JComboBox(estados);
		comboBoxEstado.setBounds(93, 252, 290, 31);
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		contentPanel.add(lblCadastroDeCidade);
		contentPanel.add(lblCidade);
		contentPanel.add(lblEstado);
		contentPanel.add(textCidade);
		contentPanel.add(comboBoxEstado);
		
		JLabel lblCidadesCadastradas = new JLabel("Cidades Cadastradas");
		lblCidadesCadastradas.setFont(new Font("Dialog", Font.BOLD, 25));
		lblCidadesCadastradas.setBounds(460, 47, 265, 21);
		contentPanel.add(lblCidadesCadastradas);
		
		List<Cidade> cidades = Conexao.listarCidades();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < cidades.size(); i++) {
			
			Cidade cidade = new Cidade();
			cidade.setCodigo(cidades.get(i).getCodigo());
			cidade.setNomeCidade(cidades.get(i).getNomeCidade());
			dados.add(cidade);
		}
		
		TableModelCidade modelo = new TableModelCidade(dados);
		
		tableCidade = new JTable();
		tableCidade.setBounds(619, 80, 139, 365);
		
		tableCidade.setModel(modelo);
		tableCidade.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCidade.getColumnModel().getColumn(0).setResizable(false);
		tableCidade.getColumnModel().getColumn(1).setPreferredWidth(210);
		tableCidade.getColumnModel().getColumn(1).setResizable(false);
		
		JScrollPane scrollPaneCidades = new JScrollPane(tableCidade);
		scrollPaneCidades.setBounds(460, 80, 298, 365);
		contentPanel.add(scrollPaneCidades);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cidade cidade = new Cidade();
				
				cidade.setNomeCidade(textCidade.getText());
				cidade.setEstado(comboBoxEstado.getSelectedItem().toString());
				
				try {
					
					Conexao.guardar(cidade);
					
				}catch(NullPointerException f) {
					
					JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
				}
				catch(Exception npe){
					
					JOptionPane.showMessageDialog(null, "Ops.. Algo deu errado: \n" +npe);
				}
				
				textCidade.setText("");
				comboBoxEstado.setSelectedItem(estados[0]);
				JOptionPane.showMessageDialog(null,"Cidade Cadastrada!");
				modelo.addCidade(cidade);
				tableCidade.getModel();
			}
		});
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSalvar.setBounds(93, 417, 90, 28);
		contentPanel.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textCidade.setText("");
				comboBoxEstado.setSelectedItem(estados[0]);
			}
		});
		btnLimpar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLimpar.setBounds(194, 417, 90, 28);
		contentPanel.add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
         	    Cadastro_Cidade.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(293, 417, 90, 28);
		contentPanel.add(btnVoltar);
	}
}
