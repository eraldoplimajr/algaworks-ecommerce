package com.algaworks.ecommerce.relacionamentos;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class RelacionamentoManyToOneTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		entityManager.getTransaction().begin();
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(produto.getPreco());
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		
//		itemPedido.setPedidoId(pedido.getId()); 	IdClass
//		itemPedido.setProdutoId(produto.getId());	IdClass
		itemPedido.setId(new ItemPedidoId());

		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);		
		
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
		Assert.assertNotNull(itemPedidoVerificacao);
		Assert.assertNotNull(itemPedidoVerificacao.getPedido());
		Assert.assertNotNull(itemPedidoVerificacao.getProduto());
	}
}
