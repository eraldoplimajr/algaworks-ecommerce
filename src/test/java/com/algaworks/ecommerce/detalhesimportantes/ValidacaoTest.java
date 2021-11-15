package com.algaworks.ecommerce.detalhesimportantes;

import java.util.Collections;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

/**
 *
 * @author Eraldo Lima
 *
 */
public class ValidacaoTest extends EntityManagerTest {
	
	@Test
	public void validarCliente() {
		
		entityManager.getTransaction().begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Jr");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setContatos(Collections.singletonMap("email", "jr@email.com"));
		cliente.setCpf("048.899.950-21");
		entityManager.merge(cliente);
		
		entityManager.getTransaction().commit();
		
	}

}
