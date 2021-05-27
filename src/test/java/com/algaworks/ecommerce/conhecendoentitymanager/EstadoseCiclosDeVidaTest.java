package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

/**
 *
 * @author Eraldo Lima
 *
 */
public class EstadoseCiclosDeVidaTest extends EntityManagerTest{
	
	@Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria(); // transient

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo); // cópia managed

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1); // managed

        entityManager.remove(categoriaGerenciada); // removed
        entityManager.persist(categoriaGerenciada); // managed

        entityManager.detach(categoriaGerenciada); // detached
    }

}
