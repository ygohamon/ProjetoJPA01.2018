package br.com.cdc.windows.cadastros;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import br.com.cdc.model.Cliente;
import br.com.cdc.model.Endereco;
import br.com.cdc.model.Estado;
import br.com.cdc.model.TableModelCliente;
import br.com.cdc.windows.Principal;

public class Cadastro_Cliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textIdade;
	private JTextField textCpf;
	private JTextField textCep;
	private JTextField textRua;
	private JTextField textBairro;
	private JTextField textNumero;
	private JTable tableClientes;
	private JTextField textSearch;

	public static void main(String[] args) {
		try {
			Cadastro_Cliente dialog = new Cadastro_Cliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setEnabled(true);
			dialog.setTitle("Cadastro de Clientes");
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Cadastro_Cliente() {
		setBounds(100, 100, 1200, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		List<Estado> estadosCombo = Conexao.listarEstados();
		
		String estados[] = new String[estadosCombo.size()];
		
		for(int i = 0; i < estadosCombo.size(); i++) {
			
			estados[i] = estadosCombo.get(i).getNome();
		}
		
		List<Cidade> cidadesCombo = Conexao.listarCidades();
		
		String cidades[] = new String[cidadesCombo.size()];
		
		for(int i = 0; i < cidadesCombo.size(); i++) {
			
			cidades[i] = cidadesCombo.get(i).getNomeCidade();
		}
		
		JLabel lblCadastroDeCliente = DefaultComponentFactory.getInstance().createTitle("Cadastro de Cliente");
		lblCadastroDeCliente.setBounds(5, 5, 422, 58);
		lblCadastroDeCliente.setFont(new Font("Dialog", Font.BOLD, 45));
		
		JLabel lblCodigo = new JLabel("Codigo.:");
		lblCodigo.setBounds(11, 106, 66, 21);
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setBounds(11, 157, 55, 21);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblIdade = new JLabel("Idade.:");
		lblIdade.setBounds(11, 208, 52, 21);
		lblIdade.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblCpf = new JLabel("CPF.:");
		lblCpf.setBounds(11, 259, 43, 21);
		lblCpf.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblEndereo = DefaultComponentFactory.getInstance().createTitle("Endere\u00E7o");
		lblEndereo.setBounds(5, 304, 114, 33);
		lblEndereo.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblRua = new JLabel("Logradouro.:");
		lblRua.setBounds(11, 412, 101, 21);
		lblRua.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblBairro = new JLabel("Bairro.:");
		lblBairro.setBounds(11, 463, 57, 21);
		lblBairro.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblCidade = new JLabel("Cidade.:");
		lblCidade.setBounds(11, 513, 64, 21);
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblEstado = new JLabel("Estado.:");
		lblEstado.setBounds(419, 513, 64, 21);
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblCep = new JLabel("CEP.:");
		lblCep.setBounds(11, 361, 44, 21);
		lblCep.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblNumero = new JLabel("Numero.:");
		lblNumero.setBounds(572, 412, 71, 21);
		lblNumero.setFont(new Font("Dialog", Font.BOLD, 16));
		
		textCodigo = new JTextField();
		textCodigo.setBounds(89, 100, 104, 33);
		textCodigo.setFont(new Font("Dialog", Font.PLAIN, 16));
		textCodigo.setColumns(10);
		textCodigo.setEditable(false);
		
		textNome = new JTextField();
		textNome.setBounds(89, 151, 430, 33);
		textNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		textNome.setColumns(10);
		
		textIdade = new JTextField();
		textIdade.setBounds(89, 202, 104, 33);
		textIdade.setFont(new Font("Dialog", Font.PLAIN, 16));
		textIdade.setColumns(10);
		
		textCpf = new JTextField();
		textCpf.setBounds(89, 253, 250, 33);
		textCpf.setFont(new Font("Dialog", Font.PLAIN, 16));
		textCpf.setColumns(10);
		
		textCep = new JTextField();
		textCep.setBounds(124, 355, 250, 33);
		textCep.setFont(new Font("Dialog", Font.PLAIN, 16));
		textCep.setColumns(10);
		
		textRua = new JTextField();
		textRua.setBounds(124, 406, 430, 33);
		textRua.setFont(new Font("Dialog", Font.PLAIN, 16));
		textRua.setColumns(10);
		
		textBairro = new JTextField();
		textBairro.setBounds(124, 457, 430, 33);
		textBairro.setFont(new Font("Dialog", Font.PLAIN, 16));
		textBairro.setColumns(10);
		
		textNumero = new JTextField();
		textNumero.setFont(new Font("Dialog", Font.PLAIN, 16));
		textNumero.setBounds(655, 406, 104, 33);
		textNumero.setColumns(10);
		
		JComboBox comboBoxCidade = new JComboBox(cidades);
		comboBoxCidade.setBounds(124, 508, 250, 31);
		comboBoxCidade.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JComboBox comboBoxEstado = new JComboBox(estados);
		comboBoxEstado.setBounds(527, 508, 250, 31);
		comboBoxEstado.setFont(new Font("Dialog", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		contentPanel.add(textCodigo);
		contentPanel.add(textNome);
		contentPanel.add(textIdade);
		contentPanel.add(textCpf);
		contentPanel.add(textCep);
		contentPanel.add(textRua);
		contentPanel.add(textBairro);
		contentPanel.add(textNumero);
		contentPanel.add(lblCadastroDeCliente);
		contentPanel.add(lblCodigo);
		contentPanel.add(lblNome);
		contentPanel.add(lblIdade);
		contentPanel.add(lblCpf);
		contentPanel.add(lblEndereo);
		contentPanel.add(lblRua);
		contentPanel.add(lblNumero);
		contentPanel.add(lblCep);
		contentPanel.add(lblBairro);
		contentPanel.add(lblCidade);
		contentPanel.add(lblEstado);
		contentPanel.add(comboBoxCidade);
		contentPanel.add(comboBoxEstado);
		
		List<Cliente> clientes = Conexao.listarClientes();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < clientes.size(); i++) {
			
			Cliente cliente = new Cliente();
			cliente.setCodigo(clientes.get(i).getCodigo());
			cliente.setNome(clientes.get(i).getNome());
			dados.add(cliente);
		}
		
		TableModelCliente modelo = new TableModelCliente(dados);
		
		tableClientes = new JTable();
		tableClientes.setShowVerticalLines(true);
		tableClientes.setShowHorizontalLines(true);
		tableClientes.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableClientes.setColumnSelectionAllowed(true);
		tableClientes.setBounds(1002, 100, 176, 505);
		
		tableClientes.setModel(modelo);
		tableClientes.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableClientes.getColumnModel().getColumn(0).setResizable(false);
		tableClientes.getColumnModel().getColumn(1).setPreferredWidth(210);
		tableClientes.getColumnModel().getColumn(1).setResizable(false);
		
		tableClientes.addMouseListener(new MouseAdapter(){
      
            @Override  
            public void mouseClicked(MouseEvent e) {  
               
            	int linha = tableClientes.getSelectedRow();
				//textCodigo.setText(tableClientes.getValueAt(linha, 0).toString());
				//textNome.setText(tableClientes.getValueAt(linha, 1).toString());
				
				Long cod = Long.valueOf(tableClientes.getValueAt(linha, 0).toString());
				Cliente cliente = Conexao.PesquisaClientePorId(cod);
				
				textCodigo.setText(cliente.getCodigo().toString());
				textNome.setText(cliente.getNome());
				textIdade.setText(cliente.getIdade());
				textCpf.setText(cliente.getCPF());
				
				String cep = cliente.getEndereco();
				System.out.println(cep);
				
				Endereco endereco = Conexao.PesquisaEnderecoPorId(cep);
				System.out.println(endereco.getRua());
				
				textCep.setText(endereco.getCep());
				textRua.setText(endereco.getRua());
				textBairro.setText(endereco.getBairro());
				textNumero.setText(endereco.getNumero());
				comboBoxCidade.setSelectedItem(cidades[0]);
				comboBoxEstado.setSelectedItem(estados[0]);
                  
            }  
        });
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente();
				Endereco endereco = new Endereco();
				
				cliente.setNome(textNome.getText());
				cliente.setIdade(textIdade.getText());
				cliente.setCPF(textCpf.getText());
				cliente.setEndereco(textCep.getText());
				endereco.setCep(textCep.getText());
				endereco.setRua(textRua.getText());
				endereco.setBairro(textBairro.getText());
				endereco.setNumero(textNumero.getText());
				endereco.setCidade(comboBoxCidade.getSelectedItem().toString());
				endereco.setEstado(comboBoxEstado.getSelectedItem().toString());
				
				try {
					Conexao.guardar(cliente);
					Conexao.guardar(endereco);
					
				}catch(NullPointerException f) {
					
					JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
				}
				catch(Exception npe){
					
					JOptionPane.showMessageDialog(null, "Ops.. Algo deu errado: \n" +npe);
				}
				
				textCodigo.setText("");
				textNome.setText("");
				textIdade.setText("");
				textCpf.setText("");
				textCep.setText("");
				textRua.setText("");
				textBairro.setText("");
				textNumero.setText("");
				comboBoxCidade.setSelectedItem(cidades[0]);
				comboBoxEstado.setSelectedItem(estados[0]);
				
				JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
				modelo.addCliente(cliente);
				tableClientes.getModel();
				
			}
		});
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSalvar.setBounds(249, 577, 90, 28);
		contentPanel.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textCodigo.setText("");
				textNome.setText("");
				textIdade.setText("");
				textCpf.setText("");
				textCep.setText("");
				textRua.setText("");
				textBairro.setText("");
				textNumero.setText("");
				comboBoxCidade.setSelectedItem(cidades[0]);
				comboBoxEstado.setSelectedItem(estados[0]);
				
			}
		});
		btnLimpar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLimpar.setBounds(351, 577, 90, 28);
		contentPanel.add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
         	    Cadastro_Cliente.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVoltar.setBounds(453, 577, 90, 28);
		contentPanel.add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane(tableClientes);
		scrollPane.setBounds(800, 100, 378, 505);
		contentPanel.add(scrollPane);
		
		JLabel lblClientes = DefaultComponentFactory.getInstance().createTitle("Clientes");
		lblClientes.setFont(new Font("Dialog", Font.BOLD, 25));
		lblClientes.setBounds(800, 29, 114, 25);
		contentPanel.add(lblClientes);
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("Dialog", Font.PLAIN, 16));
		textSearch.setBounds(847, 62, 331, 33);
		contentPanel.add(textSearch);
		textSearch.setColumns(10);
		
		ImageIcon iconS = new ImageIcon(Principal.class.getResource("/br/com/cdc/image/search.png"));
		Image imaS = iconS.getImage();
		//Image imagemC = imaC.getScaledInstance(lblClientes.getWidth(), lblClientes.getHeight(), Image.SCALE_DEFAULT);
		Image imagemS = imaS.getScaledInstance(35, 33, Image.SCALE_DEFAULT);
		Icon icoS = new ImageIcon(imagemS);
		
		JButton btnNewButton = new JButton(icoS);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String texto = textSearch.getText();
				
				List<Cliente> clientesP = Conexao.PesquisaCliente(texto);
				
				ArrayList dadosP = new ArrayList();
				
				for(int i = 0; i < clientes.size(); i++) {
					
					Cliente cliente = new Cliente();
					cliente.setCodigo(clientes.get(i).getCodigo());
					cliente.setNome(clientes.get(i).getNome());
					dadosP.add(cliente);
				}
				
				TableModelCliente modeloP = new TableModelCliente(dadosP);
				tableClientes.setModel(modeloP);
			}
		});
		btnNewButton.setBounds(800, 60, 35, 35);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(555, 577, 90, 28);
		contentPanel.add(btnNewButton_1);
	}
}
