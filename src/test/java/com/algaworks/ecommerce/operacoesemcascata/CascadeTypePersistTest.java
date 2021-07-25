package com.algaworks.ecommerce.operacoesemcascata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.SexoCliente;
import com.algaworks.ecommerce.model.StatusPedido;

/**
 *
 * @author Eraldo Lima
 *
 */
public class CascadeTypePersistTest extends EntityManagerTest {
	
	@Test
	public void persistirProdutoComCategoria() {
		Produto produto = new Produto();
		produto.setDataCriacao(LocalDateTime.now());
		produto.setPreco(BigDecimal.TEN);
		produto.setNome("Fones de ouvido");
		produto.setDescricao("A melhor qualidade de som");
		
		Categoria categoria = new Categoria();
		categoria.setNome("Informática");
		
		produto.setCategorias(Arrays.asList(categoria));	// CascadeType.PERSIST
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
				
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		Assert.assertFalse(produtoVerificacao.getCategorias().isEmpty());
	}
	
	@Test
	public void persistirPedidoComItens() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(produto.getPreco());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(produto.getPreco());
		
		pedido.setListaItemPedido(Arrays.asList(itemPedido));	// CascadeType.PERSIST
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertFalse(pedidoVerificacao.getListaItemPedido().isEmpty());
		
	}
	
	@Test
	public void persistirItemPedidoComPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(produto.getPreco());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido);	// Não é necessário CascadeType.PERSIST pq possui @MapsId.
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(produto.getPreco());
		
		entityManager.getTransaction().begin();
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertFalse(pedidoVerificacao.getListaItemPedido().isEmpty());
		
	}
	
	@Test
	public void persistirPedidoComCliente() {
		Cliente cliente = new Cliente();
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setNome("José Carlos");
		cliente.setCpf("01234567890");
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);		// CascadeType.PERSIST
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(BigDecimal.ZERO);
		pedido.setStatus(StatusPedido.AGUARDANDO);		
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertNotNull(pedidoVerificacao.getCliente());
		
	}

}
