package com.example.login.demo.service;

import com.example.login.demo.dto.Cart;
import com.example.login.demo.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartItem addItemToCart(Long userId, Long menuItemId, int quantity);
    List<CartItem> getCartItems(Long userId);
    Cart updateCartItemQuantity(Long cartItemId, int quantity);
    @Transactional
    void removeCartItem(Long itemId);

    void clearCart(long userId);



}
