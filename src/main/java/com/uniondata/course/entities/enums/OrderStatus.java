package com.uniondata.course.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANECELED(5);
	
	private int code; // Código do tipo enumerado;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() { // Para ter acesso ao mundo exterior, foi criado um método público para acessar este código;
		return code;
	}
	
	public static OrderStatus valueOf(int code)  { // Método vai ser estático pq ele vai funcionar sem precisar instanciar;
		for (OrderStatus value : OrderStatus.values()) { // Esse método ele vai retornar o valor correspondente ao número que passarmos, se passarmos 1 por ex, vai retornar WAITING_PAYMENT;
			if (value.getCode() == code) { // O método vai percorrer todos os valores possíveis do tipo enumerado, que são os 5 acima, pegando o código de cada, vendo se é igual, e retornando ele, e se não for, vai pro próximo até achar e retornar ele;
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}

// Implementamos a numeração em cada status de pedido, para futuramente na manutenção, o código não quebrar, caso tenha alguma inserção
// de novos status. Como por exemplo, colocar PACKING abaixo de PAID, onde as numerações ficarão bagunçadas. Neste caso, colocamos
// explicitamente um valor atribuido a CADA status, para não ter essa confusão;