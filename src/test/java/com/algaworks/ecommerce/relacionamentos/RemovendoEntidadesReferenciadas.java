package com.algaworks.ecommerce.relacionamentos;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class RemovendoEntidadesReferenciadas extends EntityManagerTest{
	
	@Test
	public void removerEntidadeRelacionada() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assert.assertNotNull(pedido.getListaItemPedido());
		
		entityManager.getTransaction().begin();
		pedido.getListaItemPedido().forEach(i -> entityManager.remove(i));
		entityManager.remove(pedido);
		entityManager.getTransaction().commit();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNull(pedidoVerificacao);		
	}
}
