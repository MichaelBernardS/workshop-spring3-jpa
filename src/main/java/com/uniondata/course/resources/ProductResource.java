package com.uniondata.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniondata.course.entities.Product;
import com.uniondata.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired // Fzer a injeção de dependência automaticamente;
	private ProductService service;
	
	@GetMapping // Annotation para responder a uma requisição do tipo Get do http;
	public ResponseEntity<List<Product>> findAll() { // Método que é um endpoint para acessar a lista de usuários;
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list); // Retornar o corpo da resposta, usuário u instanciado acima; Retornando a resposta de sucesso do http;
	}
	
	@GetMapping(value = "/{id}") // Endpoint para buscar um usuário por id; Requisição do tipo get, passando na url o valor do id do usuário;
	public ResponseEntity<Product> findById(@PathVariable Long id) { // Retornar apenas um usuário, recebendo o param de url dentro do endpoint do controlador Rest; @PathVariable serve para o Spring aceitar esse id e considerá-lo como url;
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

// Resource é o controlador Rest; 
// Controladores rest vão depender da camada de serviços, que por sua vez, depende da camada de acesso a dados (data repositories);