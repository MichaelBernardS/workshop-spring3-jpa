package com.uniondata.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniondata.course.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping // Annotation para responder a uma requisição do tipo Get do http;
	public ResponseEntity<User> findAll() { // Método que é um endpoint para acessar os usuários
		User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		return ResponseEntity.ok().body(u); // Retornar o corpo da resposta, usuário u instanciado acima;
	}
}