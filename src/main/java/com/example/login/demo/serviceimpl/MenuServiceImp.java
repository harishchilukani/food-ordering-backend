package com.example.login.demo.serviceimpl;

import com.example.login.demo.entity.MenuItem;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.exception.ResourceNotFoundException;
import com.example.login.demo.repository.MenuRepository;
import com.example.login.demo.repository.RestaurantRepository;
import com.example.login.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImp implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public MenuItem addMenuItems(Long id, MenuItem item) {

        RestaurantEntity restaurant=restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("restaurant not found with id "+ id));
        item.setRestaurantEntity(restaurant);
        return menuRepository.save(item);


    }

    @Override
    public List<MenuItem> getAllItemsByRestaurantId(Long id) {
        RestaurantEntity restaurant=restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("restuatant not found by id"+ id));
        List<MenuItem> allitems=menuRepository.findByRestaurantEntity(restaurant).orElseThrow(()->new RuntimeException("itenms not found in restaurant with id"+ id));
        return allitems;
    }

    @Override
    public MenuItem updateMenuItem(Long id,MenuItem item) {
        MenuItem presentitem=menuRepository.findById(id).orElseThrow(()->new RuntimeException("item not found by id"+id));
        presentitem.setName(item.getName());
        presentitem.setPrice(item.getPrice());
        menuRepository.save(presentitem);
        return presentitem;

    }

    @Override
    public Boolean deleteItem(Long id) {
        MenuItem item =menuRepository.findById(id).orElseThrow(()->new RuntimeException("item not found by given id"+id));
        menuRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MenuItem> sortingOrder(String order) {

            Sort sort=order.equalsIgnoreCase("desc") ?
                    Sort.by(Sort.Direction.DESC,"avgrating"):
                    Sort.by(Sort.Direction.ASC,"avgrating");
            return menuRepository.findAll(sort);
        }

}
