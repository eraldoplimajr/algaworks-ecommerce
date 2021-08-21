package com.algaworks.ecommerce.jpql;

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
public class FuncoesStringsTest extends EntityManagerTest {
	
	@Test
	public void aplicarFuncao() {
//		concat, length, locate, substring, lower, upper, trim
		
		String jpql = "select c.nome, length(c.nome) from Categoria c where substring(c.nome, 1, 1) = 'N'";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
	}

}
