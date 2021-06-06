package com.algaworks.ecommerce.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

/**
 *
 * @author Eraldo Lima
 *
 */
public class GerarNotaFiscalListener {
	
	NotaFiscalService notaFiscalService = new NotaFiscalService();
	
	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if(pedido.isPago() && pedido.getNotaFiscal() == null) {
			notaFiscalService.gerar(pedido);
		}
	}
	

}
