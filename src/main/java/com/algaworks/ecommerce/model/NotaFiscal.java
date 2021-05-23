package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Eraldo Lima
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class NotaFiscal {
	
	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	private Integer pedidoId;
	
	private String xml;
	
	private Date dataEmissao;
}
