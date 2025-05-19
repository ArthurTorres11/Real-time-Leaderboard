package com.br.rankup.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.rankup.backend.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByName(String Name);
}
