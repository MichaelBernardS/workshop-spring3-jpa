package com.uniondata.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniondata.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

// Não há uma implementação para esta interface, pois o próprio Spring já tem uma implementação padrão;
// Definindo o JpaRepository<User, Long> utilizando a entidade e o tipo do id da entidade, já tem uma implementação padrão pro tipo específico que definiu;