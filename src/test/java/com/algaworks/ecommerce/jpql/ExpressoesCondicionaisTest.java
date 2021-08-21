package com.algaworks.ecommerce.jpql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

/**
 *
 * @author Eraldo Lima
 *
 */
public class ExpressoesCondicionaisTest extends EntityManagerTest{
	
	@Test
	public void usarExpressaoCondicionalLike() {
		String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("nome", "a");
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarIsNull() {
		String jpql = "select p from Produto p where p.imagem is null";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarIsEmpty() {
		String jpql = "select p from Produto p where p.categorias is empty";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarMaiorMenor() {
		String jpql = "select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("precoInicial", new BigDecimal(400));
		typedQuery.setParameter("precoFinal", new BigDecimal(1500));
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarMaiorMenorComDatas() {
		String jpql = "select p from Pedido p where p.dataCriacao >= :data";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("data", LocalDateTime.now().minusDays(2));
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarBetween() {
		String jpql = "select p from Produto p where p.preco between :precoInicial and :precoFinal";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("precoInicial", new BigDecimal(499));
		typedQuery.setParameter("precoFinal", new BigDecimal(1500));
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarBetweenComDatas() {
		String jpql = "select p from Pedido p where p.dataCriacao between :dataInicial and :dataFinal";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(10));
		typedQuery.setParameter("dataFinal", LocalDateTime.now());
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}
	
	@Test
	public void usarExpressaoDiferente() {
		String jpql = "select p from Produto p where p.preco <> 100";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}

}
