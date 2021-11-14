package com.example.ecommarcerestapi.repository;

import com.example.ecommarcerestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserIdAndStatus(Long id, Integer status);
    Optional<User> findUserByUserName(String username);
    List<User> findAllByStatus(Integer status);
}
