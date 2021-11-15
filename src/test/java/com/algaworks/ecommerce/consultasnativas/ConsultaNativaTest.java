package com.algaworks.ecommerce.consultasnativas;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

/**
 *
 * @author Eraldo Lima
 *
 */
public class ConsultaNativaTest extends EntityManagerTest{
	
	@Test
	public void executarSQL() {
		String sql = "select id, nome from produto";
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();
		
		lista.forEach(arr -> System.out.println("Id: " + arr[0] + ", Nome: " + arr[1]));
	}

}
