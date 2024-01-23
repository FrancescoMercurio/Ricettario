package it.ricette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ricette.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}