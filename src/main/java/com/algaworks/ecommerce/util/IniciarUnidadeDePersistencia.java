package com.algaworks.ecommerce.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.ecommerce.model.Produto;

/**
 *
 * @author Eraldo Lima
 *
 */
public class IniciarUnidadeDePersistencia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Produto produto = entityManager.find(Produto.class, 1);
		
		System.out.println("produto.getNome() --> " + produto.getNome());
		
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
