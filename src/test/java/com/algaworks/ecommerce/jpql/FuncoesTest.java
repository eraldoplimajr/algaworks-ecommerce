package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class FuncoesTest extends EntityManagerTest{
	
	@Test
	public void aplicarFuncaoNativas() {		
		String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		
		List<Pedido> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(obj -> System.out.println(obj));
	}
	
	@Test
	public void aplicarFuncaoColecao() {
		String jpql = "select size(p.listaItemPedido) from Pedido p where size(p.listaItemPedido) > 1";
		
		TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);
		
		List<Integer> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(size -> System.out.println(size));
		
	}
	
	@Test
	public void aplicarFuncaoNumero() {		
		String jpql = "select abs(p.total), mod(p.id, 2), sqrt(p.total) from Pedido p";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2]));
	}
	
	@Test
	public void aplicarFuncaoData() {
		
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		current_date, current_time, current_timestamp
//		year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) 
		
		String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao) from Pedido p where minute(p.dataCriacao) > 5";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2]));
	}
	
	@Test
	public void aplicarFuncaoString() {
//		concat, length, locate, substring, lower, upper, trim
		
		String jpql = "select c.nome, length(c.nome) from Categoria c where substring(c.nome, 1, 1) = 'N'";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
	}

}
