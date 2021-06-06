package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "nota_fiscal")
public class NotaFiscal {
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "pedido_id")
	private Integer id;
	
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	private String xml;
	
	@Column(name = "data_emissao")
	private Date dataEmissao;
}
