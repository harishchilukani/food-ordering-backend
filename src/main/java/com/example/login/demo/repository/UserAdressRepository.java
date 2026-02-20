package com.example.login.demo.repository;

import com.example.login.demo.entity.UserAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserAdressRepository extends JpaRepository<UserAdress,Long> {
    List<UserAdress> findByUserId(Long userId);
}
