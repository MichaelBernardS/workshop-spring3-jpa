package com.uniondata.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniondata.course.entities.User;
import com.uniondata.course.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() { // Operação na camada de serviço, que repassa a chamada para o repository.findAll;
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id); // Retornando um objeto optional;
		return obj.get(); // get() do Optional retorna o objeto do tipo que estiver dentro do Optional (User);
	}
	
	public User insert(User obj) { // Operação para salvar um usuário no BD; Operação retorna o usuário salvo;
		return repository.save(obj); // Save por padrão já retorna o objeto salvo;
	}
	
	public void delete(Long id) { // Operação para deletar um usuário do BD; Passando o id do usuário para deletar;
		repository.deleteById(id);
	}
}