package it.ricette.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import it.ricette.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}