package com.example.login.demo.serviceimpl;

import com.example.login.demo.dto.Cart;
import com.example.login.demo.entity.CartItem;
import com.example.login.demo.entity.MenuItem;
import com.example.login.demo.entity.User;
import com.example.login.demo.repository.CartRespository;
import com.example.login.demo.repository.MenuRepository;
import com.example.login.demo.repository.UserRepository;
import com.example.login.demo.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    private final CartRespository cartRespository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Override
    public CartItem addItemToCart(Long userId, Long menuItemId, int quantity) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        MenuItem menuItem=menuRepository.findById(menuItemId).orElseThrow(()-> new RuntimeException("menu iteam not found"));
        CartItem cartitems=new CartItem();
        cartitems.setUser(user);
        cartitems.setMenuItem(menuItem);
        cartitems.setQuantity(quantity);
        cartitems.setPrice(menuItem.getPrice());
         return cartRespository.save(cartitems);

    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        List<CartItem> cartitems=cartRespository.findByUser(user);
//        List<Cart> arrli=new ArrayList<>();
//         for (CartItem cart : cartitems){
//            Cart dto = new Cart();
//            dto.setItemName(cart.getMenuItem().getName());
//            dto.setQuantity(cart.getQuantity());
//            dto.setPrice(cart.getMenuItem().getPrice());
//            arrli.add(dto);
//         }
         return cartitems;

    }

    @Override
    public Cart updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cart=cartRespository.findById(cartItemId).orElseThrow(()->new RuntimeException("item not found"));
        cart.setQuantity(quantity);
//        if(key.equals("decrement"))
//        {
//            int x= cart.getQuantity();
//            try{
//                if(quantity>x)
//                {
//                    throw new RuntimeException();
//                }
//                cart.setQuantity(cart.getQuantity() - quantity);
//
//            }
//            catch (RuntimeException e)
//            {
//                   throw new RuntimeException();
//            }
//
//        }
//        else {
//            cart.setQuantity(cart.getQuantity() + quantity);
//        }
           cartRespository.save(cart);
        Cart dto= new Cart();
        dto.setQuantity(cart.getQuantity());
        dto.setItemName(cart.getMenuItem().getName());
        dto.setPrice(cart.getPrice());
        return dto;




    }

    @Override
    @Transactional
    public void removeCartItem(Long itemId) {
        CartItem item=cartRespository.findById(itemId).orElseThrow(()->new RuntimeException("item not found"));
           cartRespository.deleteById(itemId);


    }

    @Override
    @Transactional
    public void clearCart(long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        cartRespository.deleteByUser(user);


    }
}
