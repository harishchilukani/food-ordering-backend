package com.example.login.demo.serviceimpl;

import com.example.login.demo.dto.OrderItemDto;
import com.example.login.demo.dto.PlaceOrderDto;
import com.example.login.demo.dto.UserAdressDto;
import com.example.login.demo.entity.*;
import com.example.login.demo.repository.*;
import com.example.login.demo.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderserviceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRespository cartRespository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderSummaryRepository orderSummaryRepository;
    @Override
    @Transactional
    public  PlaceOrderDto placeOrder(Long restauarantId,Long userId, UserAdressDto useradress) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));

        RestaurantEntity restaurant=restaurantRepository.findById(restauarantId).orElseThrow(()->new RuntimeException("restuarant not found"));

        List<CartItem> cartitems = cartRespository.findByUser(user);
        try
        {
            if (cartitems.isEmpty())
            {
                throw new RuntimeException();

            }
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }


        double totalamount=0;
        Order order=new Order();
        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("Placed");

        List<OrderItem> allItems = new ArrayList<>();


        //for orderitems
        for(CartItem i : cartitems)
        {
            OrderItem orderitem=new OrderItem();

            orderitem.setItemname(i.getMenuItem().getName());
            orderitem.setQuantity(i.getQuantity());
            orderitem.setPrice(i.getPrice());
            orderitem.setOrder(order);
            totalamount=totalamount+i.getQuantity()*i.getPrice();

            allItems.add(orderitem);
        }
        order.setItems(allItems);
        order.setTotalAmount(totalamount);
        order.setRestaurantEntity(restaurant);
        Order savedOrder = orderRepository.save(order);


        //for orderitemsdto  creating order items dtos to return item list
        List<OrderItemDto> allitems = new ArrayList<>();
        for(CartItem i : cartitems)
        {
            OrderItemDto orderitem = new OrderItemDto();


            orderitem.setQuantity(i.getQuantity());
            orderitem.setPrice(i.getPrice());
            orderitem.setItemName(i.getMenuItem().getName());


            allitems.add(orderitem);
        }
       // return placeOrderDto;
        PlaceOrderDto orders=new PlaceOrderDto();
        orders.setOrderId(savedOrder.getId());
        orders.setUserName(savedOrder.getUser().getUsername());
        orders.setTotalAmount(savedOrder.getTotalAmount());
        orders.setStatus(savedOrder.getStatus());
        orders.setOrderAt(savedOrder.getOrderTime());
        orders.setRestaurant(restaurant.getRestaurantname());
        orders.setRestaurantLongitude(restaurant.getLongitude());
        orders.setRestaurantLatitude(restaurant.getLatitude());
        orders.setRestaurantLocation(restaurant.getLocation());
        //setting delivery adress
        orders.setUserStreet(useradress.getStreet());
        orders.setUserCity(useradress.getCity());
        orders.setUserLatitude(useradress.getLatitude());
        orders.setUserLongitude(useradress.getLongitude());



        orders.setItems(allitems);
        cartRespository.deleteByUser(user);

        //saving adressdetails for delicery person
        OrderSummary adressDetails=new OrderSummary();
           adressDetails.setOrderId(savedOrder.getId());
           adressDetails.setRestaurant(restaurant.getRestaurantname());
           adressDetails.setRestaurantLocation(restaurant.getLocation());
           adressDetails.setRestaurantLatitude(restaurant.getLatitude());
           adressDetails.setRestaurantLongitude(restaurant.getLongitude());

        adressDetails.setUserStreet(useradress.getStreet());
        adressDetails.setUserCity(useradress.getCity());
        adressDetails.setUserLatitude(useradress.getLatitude());
        adressDetails.setUserLongitude(useradress.getLongitude());

        orderSummaryRepository.save(adressDetails);


        return orders;









    }

    @Override
    public List<PlaceOrderDto> getAllOrders(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException());
        List<Order> order=orderRepository.findByUserOrderByOrderTimeDesc(user);
        //creating orderitemdto

        List<PlaceOrderDto> arrli2=new ArrayList<>();
        for(Order i : order)
        {
            List<OrderItemDto> arrli1=new ArrayList<>();
            int n=i.getItems().size();
            for(int j=0;j<n;j++)
            {
                OrderItemDto orderItem = new OrderItemDto();

                orderItem.setItemName(i.getItems().get(j).getItemname());
                orderItem.setQuantity(i.getItems().get(j).getQuantity());
                orderItem.setPrice(i.getItems().get(j).getPrice());
                arrli1.add(orderItem);
            }


            PlaceOrderDto orders=new PlaceOrderDto();
            orders.setOrderId(i.getId());
            orders.setOrderAt(i.getOrderTime());
            orders.setUserName(i.getUser().getUsername());
            orders.setTotalAmount(i.getTotalAmount());
            orders.setStatus(i.getStatus());
            orders.setItems(arrli1);

            arrli2.add(orders);


        }


        return arrli2;
    }

    @Override
    public PlaceOrderDto getOrder(Long orderId) {
        Order recentOrder=orderRepository.findById(orderId) .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));
        List<OrderItemDto> arrli1=new ArrayList<>();
        int n=recentOrder.getItems().size();
        for(int j=0;j<n;j++)
        {
            OrderItemDto orderItem = new OrderItemDto();

            orderItem.setItemName(recentOrder.getItems().get(j).getItemname());
            orderItem.setQuantity(recentOrder.getItems().get(j).getQuantity());
            orderItem.setPrice(recentOrder.getItems().get(j).getPrice());
            arrli1.add(orderItem);
        }

        PlaceOrderDto order=new PlaceOrderDto();
        order.setOrderId(recentOrder.getId());
        order.setOrderAt(recentOrder.getOrderTime());
        order.setUserName(recentOrder.getUser().getUsername());
        order.setTotalAmount(recentOrder.getTotalAmount());
        order.setStatus(recentOrder.getStatus());
        order.setItems(arrli1);


        return order;
    }
}
