package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

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

}