package com.uniondata.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniondata.course.entities.Product;
import com.uniondata.course.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() { // Operação na camada de serviço, que repassa a chamada para o repository.findAll;
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id); // Retornando um objeto optional;
		return obj.get(); // get() do Optional retorna o objeto do tipo que estiver dentro do Optional (Product);
	}
}