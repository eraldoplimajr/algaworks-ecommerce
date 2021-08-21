package com.algaworks.ecommerce.jpql;

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
public class OperadoresLogicosTest extends EntityManagerTest{
	
	@Test
	public void usarOperadores() {
		String jpql = "select p from Pedido p where (p.status = 'AGUARDANDO' or p.status = 'PAGO') and p.total > 100 ";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}

}
