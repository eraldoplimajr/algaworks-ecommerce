package com.algaworks.ecommerce.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Eraldo Lima
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable{
	
	@EqualsAndHashCode.Include
	private Integer pedidoId;
	
	@EqualsAndHashCode.Include
	private Integer produtoId;

}
