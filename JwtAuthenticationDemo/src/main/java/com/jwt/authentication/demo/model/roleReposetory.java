package com.jwt.authentication.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleReposetory extends JpaRepository<Role, Long>{
	public Role findByRoleName(String roleName);
}
