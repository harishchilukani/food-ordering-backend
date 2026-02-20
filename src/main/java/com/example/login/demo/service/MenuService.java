package com.example.login.demo.service;

import com.example.login.demo.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    public MenuItem addMenuItems(Long id, MenuItem item);
   public List<MenuItem> getAllItemsByRestaurantId(Long id);
    public MenuItem updateMenuItem(Long id,MenuItem item);
    public Boolean deleteItem(Long id);
    public List<MenuItem> sortingOrder(String order);
}
