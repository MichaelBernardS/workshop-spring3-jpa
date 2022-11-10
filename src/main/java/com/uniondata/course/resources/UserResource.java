package com.uniondata.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uniondata.course.entities.User;
import com.uniondata.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired // Fzer a injeção de dependência automaticamente;
	private UserService service;
	
	@GetMapping // Annotation para responder a uma requisição do tipo Get do http;
	public ResponseEntity<List<User>> findAll() { // Método que é um endpoint para acessar a lista de usuários;
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); // Retornar o corpo da resposta, usuário u instanciado acima; Retornando a resposta de sucesso do http;
	}
	
	@GetMapping(value = "/{id}") // Endpoint para recuperar dados de um BD, no caso um usuário por id; Requisição do tipo get, passando na url o valor do id do usuário;
	public ResponseEntity<User> findById(@PathVariable Long id) { // Retornar apenas um usuário, recebendo o param de url dentro do endpoint do controlador Rest; @PathVariable serve para o Spring aceitar esse id e considerá-lo como url;
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping // Método post do http para inserir um novo usuário no BD; Toda vez que for fzer uma inserção no BD, usa-se o método Post;
	public ResponseEntity<User> insert(@RequestBody User obj)  { // Para dizer que o obj vai chegar no modo json, e esse json vai ser desserializado para um objeto do tipo User do Java, usa-se o Annotation @RequestBody;
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}

// Resource é o controlador Rest; 
// Controladores rest vão depender da camada de serviços, que por sua vez, depende da camada de acesso a dados (data repositories);