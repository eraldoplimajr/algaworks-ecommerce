package com.algaworks.ecommerce.iniciandocomjpa;

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
