package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
@Table(name = "item_pedido")
public class ItemPedido {
	
	@EmbeddedId
	private ItemPedidoId id;
	
	@NotNull
	@MapsId("pedidoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
	private Pedido pedido;
	
	@NotNull
	@MapsId("produtoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
	private Produto produto;
	
	@NotNull
	@Positive
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal precoProduto;
	
	@NotNull
	@Positive
	@Column(nullable = false)
	private Integer quantidade;	
}
