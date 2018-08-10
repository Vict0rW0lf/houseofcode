package com.houseofcode.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.houseofcode.store.models.Product;

@Repository
@Transactional
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("Select p from Product p", Product.class)
						.getResultList();
	}

	public Product findBy(Long id) {
		return manager.createQuery("Select distinct(p) from Product p join fetch p.prices where p.id = :id", Product.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
 