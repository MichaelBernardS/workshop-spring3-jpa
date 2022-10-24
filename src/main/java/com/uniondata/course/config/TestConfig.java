package com.uniondata.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.uniondata.course.entities.User;
import com.uniondata.course.repositories.UserRepository;

@Configuration // Especificar para o Spring que esta classe é uma classe de configuração, através do annotation configuration;
@Profile("test") // Classe específica para a versão de teste, este nome precisa ser exatamente o mesmo q está no properties(profile);
public class TestConfig implements CommandLineRunner { // CommandLineRunner - Macete de quando o Spring for iniciado, tudo já é executado;

	@Autowired // Para que o Spring consiga injetar a dependência automaticamente e associar uma instância do UserRepository no TestConfig, basta colocar o annotation Autowired;
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception { // Implementação da interface CommandLineRunner, onde tem que implementar o run;
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2)); // Operação para salvar a lista passada no BD;
	}
}

// Classe de configuração para o perfil de teste; Classe auxiliar que faz algumas configurações na aplicação;