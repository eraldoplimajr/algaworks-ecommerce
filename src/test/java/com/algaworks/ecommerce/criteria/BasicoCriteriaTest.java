package com.algaworks.ecommerce.criteria;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

/**
 *
 * @author Eraldo Lima
 *
 */
public class BasicoCriteriaTest extends EntityManagerTest {
	
	@Test
	public void projetarOResultado() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		
		criteriaQuery.multiselect(root.get("id"), root.get("nome"));
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println("Id: " + arr[0] + ", Nome: " + arr[1]));
	}
	
	@Test
	public void retornarTodosOsProdutosExercicio() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		
		criteriaQuery.select(root);
		
		TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Produto> produtos = typedQuery.getResultList();
		
		Assert.assertFalse(produtos.isEmpty());		
	}
	
	@Test
	public void selecionarUmAtributoParaRetorno() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
		
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Cliente cliente = typedQuery.getSingleResult();
		
		Assert.assertEquals("Fernando Medeiros", cliente.getNome());		
	}
	
	@Test
	public void buscarPorIdentificador() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Pedido pedido = typedQuery.getSingleResult();
		
		Assert.assertNotNull(pedido);		
	}
}
