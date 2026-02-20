package com.example.login.demo.repository;

import com.example.login.demo.entity.CartItem;
import com.example.login.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRespository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
    void deleteAllByUser(User user);
   // Optional<CartItem> findByUserIdAndMenuItemId(Long userId, Long menuItemId);

}
