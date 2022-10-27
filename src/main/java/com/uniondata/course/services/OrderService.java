package com.uniondata.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniondata.course.entities.Order;
import com.uniondata.course.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() { // Operação na camada de serviço, que repassa a chamada para o repository.findAll;
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id); // Retornando um objeto optional;
		return obj.get(); // get() do Optional retorna o objeto do tipo que estiver dentro do Optional (Order);
	}
}