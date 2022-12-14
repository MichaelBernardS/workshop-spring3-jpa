package com.uniondata.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.uniondata.course.entities.Category;
import com.uniondata.course.entities.Order;
import com.uniondata.course.entities.OrderItem;
import com.uniondata.course.entities.Payment;
import com.uniondata.course.entities.Product;
import com.uniondata.course.entities.User;
import com.uniondata.course.entities.enums.OrderStatus;
import com.uniondata.course.repositories.CategoryRepository;
import com.uniondata.course.repositories.OrderItemRepository;
import com.uniondata.course.repositories.OrderRepository;
import com.uniondata.course.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception { // Implementação da interface CommandLineRunner, onde tem que implementar o run;
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2); // Associando o produto a categoria específica; Associação entre objetos;
		p2.getCategories().add(cat1); // Paradigma orientado a objetos, e não relacional, do banco de dados;
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT ,u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT ,u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2)); // Operação para salvar a lista passada no BD;
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1); // Associação de mão dupla em memória; Não usaremos o repository do proprio objeto para salvar um objeto dependente numa relação um para um;
		orderRepository.save(o1); // JPA vai salvar no BD este pagamento a este pedido, funciona desta forma a relação de associação do JPA;
	}
}

// Arquivo seed - Classe de configuração para o perfil de teste; Classe auxiliar que faz algumas configurações na aplicação;