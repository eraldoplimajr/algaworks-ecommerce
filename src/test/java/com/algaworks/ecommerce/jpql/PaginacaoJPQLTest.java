package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

/**
 *
 * @author Eraldo Lima
 *
 */
public class PaginacaoJPQLTest extends EntityManagerTest{
	
	@Test
	public void paginarResultados() {
		String jpql = "select c from Categoria c order by c.id asc";
		
//		FIRST_RESULT = MAX_RESULTS * (pagina - 1)
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(2);
		
		List<Categoria> lista = typedQuery.getResultList();
		
		lista.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
	}

}
