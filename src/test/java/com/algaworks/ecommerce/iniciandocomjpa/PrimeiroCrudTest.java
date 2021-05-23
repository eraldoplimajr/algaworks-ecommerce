package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

/**
 *
 * @author Eraldo Lima
 *
 */
public class PrimeiroCrudTest extends EntityManagerTest{
	
	@Test
	public void consultarRegistroPorIdentificador() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertNotNull(cliente);
	}
	
	@Test
	public void inserirRegistro() {
		Cliente cliente = new Cliente();
		
		cliente.setId(3);
		cliente.setNome("Eraldo Lima");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
		Assert.assertEquals("Eraldo Lima", clienteVerificacao.getNome());
	}
	
	@Test
	public void atualizarRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		cliente.setNome("Ronaldinho Gaúcho");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals("Ronaldinho Gaúcho", clienteVerificacao.getNome());
	}
	
	@Test
	public void removerRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
				
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNull(clienteVerificacao);
	}
}
