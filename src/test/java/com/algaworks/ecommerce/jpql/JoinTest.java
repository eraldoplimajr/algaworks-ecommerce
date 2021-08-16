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
public class JoinTest extends EntityManagerTest {
	
	@Test
	public void fazerJoin() {
		
		String jpql = "select p from Pedido p join p.pagamento pag";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertTrue(lista.size() == 1);
		
	}
	
	@Test
	public void fazerLeftJoin() {
		
		String jpql = "select p from Pedido p left join p.pagamento pag";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertTrue(lista.size() == 2);
		
	}
	
	@Test
	public void usarJoinFetch() {
		
		String jpql = "select p from Pedido p "
				+ " left join fetch p.pagamento "
				+ " join fetch p.cliente "
				+ " left join fetch p.notaFiscal ";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
	}

}
