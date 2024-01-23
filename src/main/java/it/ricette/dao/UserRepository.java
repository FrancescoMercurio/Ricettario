package it.ricette.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import it.ricette.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}