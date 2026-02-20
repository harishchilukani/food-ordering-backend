package com.example.login.demo.controller;

import com.example.login.demo.dto.Cart;
import com.example.login.demo.entity.CartItem;
import com.example.login.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping("/additemtocart/{userId}/{menuItemId}/{quantity}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long userId,@PathVariable Long menuItemId,@PathVariable int quantity)
    {
        cartService.addItemToCart(userId,menuItemId,quantity);
        return new ResponseEntity<>("item added to cart sucessfully", HttpStatus.OK);

    }
    @GetMapping("/getcartitems/{userId}")
    public ResponseEntity<?> getCartItems(@PathVariable  Long userId)
    {
        List<CartItem> cartitems=cartService.getCartItems(userId);
        double totalPrice = cartitems.stream()
                .mapToDouble(cart -> cart.getPrice() * cart.getQuantity())
                .sum();
        int totalQuantity = cartitems.stream()
                .mapToInt(cart -> cart.getQuantity())
                .sum();
        Map<String,?> map=Map.of("cartitems", cartitems, "totalprice", totalPrice,"totalQuantity",totalQuantity);

        return new ResponseEntity<>(map,HttpStatus.OK);

    }
    @PutMapping("/updatequantity/{cartItemId}/{quantity}")
    public ResponseEntity<Cart> updatequantity(@PathVariable Long cartItemId, @PathVariable int quantity)
    {
        try {
            Cart updatedcart = cartService.updateCartItemQuantity(cartItemId,quantity);
            return new ResponseEntity<>(updatedcart, HttpStatus.OK);
        }
        catch (RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/removecartitem/{itemId}")
    public void removeCartItem(@PathVariable Long itemId) {
        cartService.removeCartItem(itemId);
    }

    @DeleteMapping("/clearcart/{userId}")
    public void clearCart(@PathVariable Long userId)
    {
        cartService.clearCart(userId);
    }

}
