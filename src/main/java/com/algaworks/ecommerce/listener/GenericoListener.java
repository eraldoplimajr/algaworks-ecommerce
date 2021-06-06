package com.algaworks.ecommerce.listener;

import javax.persistence.PostLoad;

/**
 *
 * @author Eraldo Lima
 *
 */
public class GenericoListener {
	
	@PostLoad
	public void logCarregamento(Object obj) {
		System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
	}

}
