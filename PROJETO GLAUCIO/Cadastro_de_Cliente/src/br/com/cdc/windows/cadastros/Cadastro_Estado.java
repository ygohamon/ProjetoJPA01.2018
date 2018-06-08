package br.com.cdc.windows.cadastros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import br.com.cdc.connection.Conexao;
import br.com.cdc.model.Estado;
import br.com.cdc.model.TableModelEstado;
import br.com.cdc.windows.Principal;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Cadastro_Estado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textEstado;
	private JTable tableEstados;

	public static void main(String[] args) {
		try {
			Cadastro_Estado dialog = new Cadastro_Estado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setEnabled(true);
			dialog.setTitle("Cadastro de Estado");
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cadastro_Estado() {
		
		setBounds(100, 100, 780, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCadastroDeEstado = DefaultComponentFactory.getInstance().createTitle("Cadastro de Estado");
		lblCadastroDeEstado.setBounds(5, 5, 422, 58);
		lblCadastroDeEstado.setFont(new Font("Dialog", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Estado.: ");
		lblNome.setBounds(5, 215, 68, 21);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 16));
		
		textEstado = new JTextField();
		textEstado.setBounds(91, 209, 290, 33);
		textEstado.setFont(new Font("Dialog", Font.PLAIN, 16));
		textEstado.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblCadastroDeEstado);
		contentPanel.add(lblNome);
		contentPanel.add(textEstado);
		
		JLabel lblEstadosCadastrados = new JLabel("Estados Cadastradas");
		lblEstadosCadastrados.setFont(new Font("Dialog", Font.BOLD, 25));
		lblEstadosCadastrados.setBounds(461, 47, 269, 21);
		contentPanel.add(lblEstadosCadastrados);
		
		List<Estado> estados = Conexao.listarEstados();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < estados.size(); i++) {
			
			Estado estado = new Estado();
			estado.setCodigo(estados.get(i).getCodigo());
			estado.setNome(estados.get(i).getNome());
			dados.add(estado);
		}
		
		TableModelEstado modelo = new TableModelEstado(dados);
		
		tableEstados = new JTable();
		tableEstados.setBounds(610, 80, 148, 355);
		contentPanel.add(tableEstados);
		
		tableEstados.setModel(modelo);
		tableEstados.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEstados.getColumnModel().getColumn(0).setResizable(false);
		tableEstados.getColumnModel().getColumn(1).setPreferredWidth(210);
		tableEstados.getColumnModel().getColumn(1).setResizable(false);

		JScrollPane scrollPane = new JScrollPane(tableEstados);
		scrollPane.setBounds(461, 78, 297, 357);
		contentPanel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estado estado = new Estado();
				
				estado.setNome(textEstado.getText());
				
				try {
					
					Conexao.guardar(estado);
					
				}catch(NullPointerException f) {
					
					JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
				}
				catch(Exception npe){
					
					JOptionPane.showMessageDialog(null, "Ops.. Algo deu errado: \n" +npe);
				}
				
				textEstado.setText("");
				JOptionPane.showMessageDialog(null, "Estado Cadastrado!");
				modelo.addEstado(estado);
				tableEstados.getModel();
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(91, 407, 90, 28);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textEstado.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_1.setBounds(193, 407, 90, 28);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
         	    Cadastro_Estado.this.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_2.setBounds(295, 407, 90, 28);
		contentPanel.add(btnNewButton_2);
	}
}
