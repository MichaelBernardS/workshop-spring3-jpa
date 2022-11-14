package com.uniondata.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uniondata.course.entities.User;
import com.uniondata.course.repositories.UserRepository;
import com.uniondata.course.services.exceptions.DatabaseException;
import com.uniondata.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() { // Operação na camada de serviço, que repassa a chamada para o repository.findAll;
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id); // Retornando um objeto optional;
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // get() do Optional retorna o objeto do tipo que estiver dentro do Optional (User); Caso tenha a exceção, basta chamá-la e não o get().
	}
	
	public User insert(User obj) { // Operação para salvar um usuário no BD; Operação retorna o usuário salvo;
		return repository.save(obj); // Save por padrão já retorna o objeto salvo;
	}
	
	public void delete(Long id) { // Operação para deletar um usuário do BD; Passando o id do usuário para deletar;
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // Tratando a exceção específica que dá ao tentar deletar um usuário que não tem no BD; 
			throw new ResourceNotFoundException(id); // Lançando a exceção que criamos;
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage()); // Lançando uma exceção da nossa camada de serviço, que criamos para tratar específicamente este tipo de erro; Para dar o erro 404 "correto" ao apagar um usuário que há pedidos vinculados a ele no BD;
		}
	}
	
	public User update(Long id, User obj) { // Operação para atualizar um usuário no BD; Passando o id para saber qual usuário vai atualizar, e o objeto User contendo os dados para serem atualizados;
		User entity = repository.getReferenceById(id); // Instancia um usuário, porém não vai pro BD ainda, só vai deixar um objeto monitorado pelo JPA, para trabalhar com ele, e em seguida, pode efetuar alguma operação com o BD; Diferente do findById que pega o objeto e trás p nós, já o referenceId prepara o objeto p mexer e dps efetuar uma operação c o BD, é mais eficiente desta forma;
		updateData(entity, obj); // Atualizar os dados do entity, baseado nos dados que chegaram no obj;
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}