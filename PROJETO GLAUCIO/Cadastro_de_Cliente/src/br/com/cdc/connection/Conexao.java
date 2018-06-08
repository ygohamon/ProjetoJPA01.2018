package br.com.cdc.connection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.cdc.model.Cidade;
import br.com.cdc.model.Cliente;
import br.com.cdc.model.Endereco;
import br.com.cdc.model.Estado;
import br.com.cdc.model.Produto;
import br.com.cdc.model.Tipo_Produto;
import br.com.cdc.model.Venda;

public class Conexao {
	
public static EntityManagerFactory emf;
	
	public static void iniciarFabrica() {
		emf = Persistence.createEntityManagerFactory("CDC"); 
	}
	
	public static void guardar(Object o) {
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(o);
		et.commit();
		em.close();
	}
	
	public static List<Estado> listarEstados() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Estado");
		
		List<Estado> estados = query.getResultList();
        return estados;
	}
	
	public static List<Produto> listarProduto() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Produto");
		
		List<Produto> produtos = query.getResultList();
        return produtos;
	}
	
	public static List<Tipo_Produto> listarTipo_Produto() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Tipo_Produto");
		
		List<Tipo_Produto> tprodutos = query.getResultList();
        return tprodutos;
	}
	
	public static List<Venda> listarVenda() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Venda");
		
		List<Venda> vendas = query.getResultList();
        return vendas;
	}
	
	public static List<Cidade> listarCidades() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cidade");
		
		List<Cidade> cidades = query.getResultList();
        return cidades;
	}
	
	public static List<Cliente> listarClientes() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cliente");
		
		List<Cliente> clientes = query.getResultList();
        return clientes;
	}
	
	public static List<Cliente> PesquisaCliente(String nome){
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cliente c WHERE c.nome LIKE :nome ORDER BY c.nome ");
		
		List<Cliente> clientes = query.getResultList();
        return clientes;
	}
	
	public static Cliente PesquisaClientePorId(Long codigo){
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cliente c WHERE c.codigo = :codigo");
		query.setParameter("codigo", codigo); 
		Cliente cliente = (Cliente) query.getSingleResult();
        return cliente;
	}
	
	public static Endereco PesquisaEnderecoPorId(String cep){
		
		try {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Endereco c WHERE c.cep = :cep");
		query.setParameter("cep", cep); 
		Endereco endereco = (Endereco) query.getSingleResult();
        return endereco;
		}catch (NoResultException nre){
			JOptionPane.showMessageDialog(null, "Parece que deu erro na hora de buscar o cep :(" +nre);
			return null;
		}
	}

}
