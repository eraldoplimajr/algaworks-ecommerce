package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

/**
 *
 * @author Eraldo Lima
 *
 */
public class OperacoesComTransacaoTest extends EntityManagerTest{
	
	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
//		entityManager.clear(); não é necessário, pois o remove também limpa da memória do entityManager
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjeto() {
		
		Produto produto = new Produto();
		produto.setId(2);
		produto.setNome("Câmera Canon");
		produto.setDescicao("A melhor definição para suas fotos.");
		produto.setPreco(new BigDecimal(5000));
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		
	}
	
	@Test
	public void abrirEFecharATransacao() {
		
		Produto produto = new Produto(); //somente para o método não mostra erros
		
		entityManager.getTransaction().begin();
		/*
		 * entityManager.persist(produto); 
		 * entityManager.merge(produto);
		 * entityManager.remove(produto);
		 */	
		entityManager.getTransaction().commit();
		
	}
}
