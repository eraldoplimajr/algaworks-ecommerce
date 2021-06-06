package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Pedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class NotaFiscalService {
	
	public void gerar(Pedido pedido) {
		System.out.println("Gerando nota para o pedido " + pedido.getId() +".");
	}

}
