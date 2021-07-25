package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDate;

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
public class SecondaryTableTest extends EntityManagerTest {
	
	@Test
	public void salvarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Finotti");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
		cliente.setCpf("444.444.444-44");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao.getSexo());
	}

}
