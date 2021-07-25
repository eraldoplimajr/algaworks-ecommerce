package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

/**
 *
 * @author Eraldo Lima
 *
 */
public class MapeandoEnumeracoesTest extends EntityManagerTest{
	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente();
		
		cliente.setNome("José Mineiro");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setCpf("555.555.555-55");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
	}
}
