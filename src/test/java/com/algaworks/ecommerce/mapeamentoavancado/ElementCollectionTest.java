package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.SexoCliente;

/**
 *
 * @author Eraldo Lima
 *
 */
public class ElementCollectionTest extends EntityManagerTest {
	
	@Test
	public void aplicarTest() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setTags(Arrays.asList("ebook" , "livro-digital"));		
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
	}
	
	@Test
	public void aplicarAtributos() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setAtributos(Arrays.asList(new Atributo("display" , "320x600")));		
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertFalse(produtoVerificacao.getAtributos().isEmpty());
	}
	
	@Test
	public void aplicarContato() {
		entityManager.getTransaction().begin();
				
		Cliente cliente = entityManager.find(Cliente.class, 1);
		cliente.setContatos(Collections.singletonMap("email", "fernando@email.com"));
		cliente.setSexo(SexoCliente.MASCULINO);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals("fernando@email.com", clienteVerificacao.getContatos().get("email"));
	}

}
