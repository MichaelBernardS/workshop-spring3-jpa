package com.uniondata.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uniondata.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus; // Mudado pra Integer, para dizer explicitamente que estamos gravando no BD um numero inteiro;
	
	@ManyToOne // Pro JPA saber que é uma chave estrangeira no BD; Muitos para um; Mtos pedidos para um cliente;
	@JoinColumn(name = "client_id") // Nome da chave estrangeira (client_id) que vai conter o id do usuário associado ao pedido no BD;
	private User client;
	
	@OneToMany(mappedBy = "id.order") // No OrderItem tem o Id, e o Id por sua vez, tem o pedido;
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // Nome do atributo lá no Payment; Um para um, estamos mapeando as duas entidades para ter o mesmo ID; Ou seja, se o pedido for código 5, o pagamento tb vai ser o código 5;
 	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus); // Pegando o numero inteiro interno da classe e convertendo ele pra OrderStatus através do método valueOf;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) { // Testando pois caso o programador passa o valor nulo pra construir o objeto;
			this.orderStatus = orderStatus.getCode(); // Processo inverso, recebendo um OrderStatus e guardando internamente um número inteiro; Para pegar o número inteiro correspondente ao OrderStatus, basta chamar o getCode que retorna este código;
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems() {
		return items;
	}
	
	public Double getTotal() { // Utilizando o padrão EE com o get;
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}