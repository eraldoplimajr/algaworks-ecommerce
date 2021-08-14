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
public class BasicoJPQLTest extends EntityManagerTest{
	
	@Test
	public void buscarPorIdentificador() {
		
//		JPQL - select p from Pedido p where p.id = 1		
//		SQL - select p.* from pedido p where p.id = 1		
//		entityManager.find(Pedido.class, 1)
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
		Pedido pedido = typedQuery.getSingleResult();
		
		Assert.assertNotNull(pedido);
		
		List<Pedido> pedidos = typedQuery.getResultList();		
		Assert.assertFalse(pedidos.isEmpty());		
		
	}

}
