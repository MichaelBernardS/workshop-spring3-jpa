package com.uniondata.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id: " + id); // Construtor da superclasse com o método super, com a msg de erro mais o id que não encontrou;
	}

}

// Classe de exceção personalizada, para tratar exceções básicas, e não aceitar as padrões, pois os erros não são os "corretos";