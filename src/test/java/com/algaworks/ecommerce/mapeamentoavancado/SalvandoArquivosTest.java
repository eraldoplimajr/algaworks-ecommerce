package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

/**
 *
 * @author Eraldo Lima
 *
 */
public class SalvandoArquivosTest extends EntityManagerTest{

	
	@Test
	public void salvarXmlNota() {
		
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(carregarArquivo("/nota-fiscal.xml"));
		
		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assert.assertNotNull(notaFiscalVerificacao.getXml());
		Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);
		
		/*
		 * try { OutputStream out = new FileOutputStream(
		 * Files.createFile(Paths.get(System.getProperty("user.home") +
		 * "/xml.xml")).toFile()); out.write(notaFiscalVerificacao.getXml()); } catch
		 * (Exception e) { throw new RuntimeException(e); }
		 */
		 
	}

	/**
	 * @return
	 */
	private static byte[] carregarArquivo(String src) {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream(src).readAllBytes();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void salvarImagem() {
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setImagem(carregarArquivo("/kindle.jpg"));
				
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao.getImagem());
		Assert.assertTrue(produtoVerificacao.getImagem().length > 0);
		/*
		 * try { OutputStream out = new FileOutputStream(
		 * Files.createFile(Paths.get(System.getProperty("user.home") +
		 * "/kindle.jpg")).toFile()); out.write(produtoVerificacao.getImagem()); } catch
		 * (Exception e) { throw new RuntimeException(e); }
		 */
	}
}
