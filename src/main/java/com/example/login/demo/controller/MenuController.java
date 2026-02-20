package com.example.login.demo.controller;

import com.example.login.demo.entity.MenuItem;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @PostMapping("restaurant/{id}/menu")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable Long id, @RequestBody  MenuItem item)
    {
        MenuItem created=menuService.addMenuItems(id,item);
        return new ResponseEntity<>(created,HttpStatus.OK);
    }
    @GetMapping("restaurant/{id}/allitems")
    public ResponseEntity<List<MenuItem>> getAllItemsByRestaurantId(@PathVariable Long id)
    {
        List<MenuItem> allitems=menuService.getAllItemsByRestaurantId(id);
        return new ResponseEntity<>(allitems,HttpStatus.OK);

    }
    @PutMapping("restaurant/item/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id,@RequestBody MenuItem item)
    {
        MenuItem updateditem=menuService.updateMenuItem(id,item);
        return new ResponseEntity<>(updateditem,HttpStatus.OK);
    }
    @DeleteMapping("restaurant/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id)
    {
        boolean t=menuService.deleteItem(id);
        return new ResponseEntity<>("item deleted sucessfully",HttpStatus.OK);
    }
    @GetMapping("/sort/{order}")
    public ResponseEntity<List<MenuItem>> sorting(@PathVariable String order)
    {
        List<MenuItem> res=menuService.sortingOrder(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
