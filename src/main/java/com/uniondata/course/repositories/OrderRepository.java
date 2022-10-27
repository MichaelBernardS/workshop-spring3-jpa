package com.uniondata.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniondata.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> { // Neste caso não precisa colocar o annotation repository, pois já está herdando o jpaRepository que já está registrado como um componente do Spring;
}

// Não há uma implementação para esta interface, pois o próprio Spring já tem uma implementação padrão;
// Definindo o JpaRepository<User, Long> utilizando a entidade e o tipo do id da entidade, já tem uma implementação padrão pro tipo específico que definiu;