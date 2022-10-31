package com.uniondata.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.uniondata.course.entities.Category;
import com.uniondata.course.entities.Order;
import com.uniondata.course.entities.User;
import com.uniondata.course.entities.enums.OrderStatus;
import com.uniondata.course.repositories.CategoryRepository;
import com.uniondata.course.repositories.OrderRepository;
import com.uniondata.course.repositories.UserRepository;

@Configuration // Especificar para o Spring que esta classe é uma classe de configuração, através do annotation configuration;
@Profile("test") // Classe específica para a versão de teste, este nome precisa ser exatamente o mesmo q está no properties(profile);
public class TestConfig implements CommandLineRunner { // CommandLineRunner - Macete de quando o Spring for iniciado, tudo já é executado;

	@Autowired // Para que o Spring consiga injetar a dependência automaticamente e associar uma instância do UserRepository no TestConfig, basta colocar o annotation Autowired;
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository; // Para acessar o banco de dados com relação aos pedidos, é preciso fazer esta classe;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception { // Implementação da interface CommandLineRunner, onde tem que implementar o run;
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT ,u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT ,u1);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		userRepository.saveAll(Arrays.asList(u1, u2)); // Operação para salvar a lista passada no BD;
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}

// Arquivo seed - Classe de configuração para o perfil de teste; Classe auxiliar que faz algumas configurações na aplicação;