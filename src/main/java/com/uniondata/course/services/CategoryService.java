package com.uniondata.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniondata.course.entities.Category;
import com.uniondata.course.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() { // Operação na camada de serviço, que repassa a chamada para o repository.findAll;
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id); // Retornando um objeto optional;
		return obj.get(); // get() do Optional retorna o objeto do tipo que estiver dentro do Optional (Category);
	}
}