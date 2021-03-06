package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Eraldo Lima
 *
 */
@Getter
@Setter
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;
	
	@NotEmpty
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> listaItemPedido;
	
	@NotNull
	@PastOrPresent
	@Column(name = "data_criacao", updatable = false, length = 6, nullable = false)
	private LocalDateTime dataCriacao;
	
	@PastOrPresent
	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;
	
	@PastOrPresent
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;
	
	@NotNull
	@Positive
	@Column(nullable = false)
	private BigDecimal total;
	
	@NotNull
	@Column(length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;
	
	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;
	
	public boolean isPago() {
		return status.equals(StatusPedido.PAGO);
	}
	
//	@PrePersist
//	@PreUpdate
	public void calcularTotal() {
		if(listaItemPedido != null) {
			total = listaItemPedido.stream()
								   .map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto())).reduce(BigDecimal.ZERO, BigDecimal::add);
		}else {
			total = BigDecimal.ZERO;
		}
	}
	
	@PrePersist
	public void aoPersistir() {
		dataCriacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreUpdate
	public void aoAtualizar() {
		dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PostUpdate
	public void aposAtualizar() {
		System.out.println("Ap??s atualizar o pedido");
	}
	
	@PreRemove
	public void aoRemover() {
		System.out.println("Antes de remover o pedido");
	}
	
	@PostRemove
	public void aposRemover() {
		System.out.println("Ap??s remover o pedido");
	}
	
	@PostLoad
	public void aoCarregar() {
		System.out.println("Ap??s carregar o pedido");
	}
}
