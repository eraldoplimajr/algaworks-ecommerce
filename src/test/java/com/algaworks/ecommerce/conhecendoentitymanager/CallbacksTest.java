package com.algaworks.ecommerce.conhecendoentitymanager;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class CallbacksTest extends EntityManagerTest{
	
	@Test
	public void acionarCallbacks() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		Produto produto = entityManager.find(Produto.class, 1);
		Produto produto2 = entityManager.find(Produto.class, 3);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		
		ItemPedido itemPedido2 = new ItemPedido();
		itemPedido2.setProduto(produto2);
		itemPedido2.setPrecoProduto(produto2.getPreco());
		itemPedido2.setQuantidade(1);
		
		List<ItemPedido> itensPedido = Arrays.asList(itemPedido, itemPedido2);
		pedido.setListaItemPedido(itensPedido);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.flush();
		
		pedido.setStatus(StatusPedido.PAGO);
		entityManager.getTransaction().commit();
		
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
		Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
	}

}
