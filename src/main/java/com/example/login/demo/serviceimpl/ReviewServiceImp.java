package com.example.login.demo.serviceimpl;

import com.example.login.demo.entity.MenuItem;
import com.example.login.demo.entity.RestaurantEntity;
import com.example.login.demo.entity.Review;
import com.example.login.demo.entity.User;
import com.example.login.demo.repository.MenuRepository;
import com.example.login.demo.repository.RestaurantRepository;
import com.example.login.demo.repository.ReviewRepository;
import com.example.login.demo.repository.UserRepository;
import com.example.login.demo.service.RestaurantService;
import com.example.login.demo.service.ReviewService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;

    @Override
    public void postReviewRestaurant(Long  userId,Long  restaurantId, Review review) {
        RestaurantEntity res= restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("user not found"));
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
       Optional<Review > existingreview=reviewRepository.findByUserIdAndRestaurantEntityId(userId,restaurantId);
       if(existingreview.isEmpty()) {
           Review reviewobj = new Review();
           reviewobj.setComment(review.getComment());
           reviewobj.setRating(review.getRating());
           reviewobj.setReviewdAt(LocalDateTime.now());
           reviewobj.setUser(user);
           reviewobj.setRestaurantEntity(res);
           reviewobj.setMenuitem(null);
           reviewRepository.save(reviewobj);
           double avgrating=getAverageRatingForRestaurant(restaurantId);
           res.setAvgrating(avgrating);
           restaurantRepository.save(res);
       }
       else
       {
           throw new EntityExistsException("review already posted");
       }



    }
    public void postReviewMenuItem(Long  userId,Long  menuItemId, Review review) {
        MenuItem menuItem= menuRepository.findById(menuItemId).orElseThrow(()-> new RuntimeException("menu not found"));
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Optional<Review > existingreview=reviewRepository.findByUserIdAndMenuitemId(userId,menuItemId);
        if(existingreview.isEmpty()) {


            Review reviewobj = new Review();
            reviewobj.setComment((review.getComment()));
            reviewobj.setRating(review.getRating());
            reviewobj.setReviewdAt(LocalDateTime.now());
            reviewobj.setUser(user);
            reviewobj.setMenuitem(menuItem);
            reviewobj.setRestaurantEntity(null);
            reviewRepository.save(reviewobj);
            double avgrating=getAverageRatingForMenuItem(menuItemId);
            menuItem.setAvgrating(avgrating);
            menuRepository.save(menuItem);
        }
        else {
            throw  new EntityExistsException("review already posted");
        }


    }

    @Override
    public List<Review> getMenuItemReview(Long menuItemId) {
        List<Review> getAllMenuItemReview= reviewRepository.findByMenuitemId(menuItemId);
        return getAllMenuItemReview;


    }

    @Override
    public List<Review> getRestaurantReview(Long restaurantId) {
        List<Review> getrestaurantReview= reviewRepository.findByRestaurantEntityId(restaurantId);
        return getrestaurantReview;

    }

    @Override
    public Review getreviewByuserToRestaurant(Long userId, Long restaurantId) {
        Review review=reviewRepository.findByUserIdAndRestaurantEntityId(userId,restaurantId).orElseThrow(()->new RuntimeException());
        return review;
    }
    public Review getreviewByuserToMenuItem(Long userId, Long menuItemId) {
        Review review=reviewRepository.findByUserIdAndMenuitemId(userId,menuItemId).orElseThrow(()->new RuntimeException());
        return review;
    }

    @Override
    public Double getAverageRatingForRestaurant(Long restaurantId) {
        List<Review> allReviews=reviewRepository.findByRestaurantEntityId(restaurantId);
        return allReviews.stream().mapToDouble(Review ::getRating).average().orElse(0.0);

    }

    @Override
    public Double getAverageRatingForMenuItem(Long menuItemId) {
        List<Review> allReviews=reviewRepository.findByMenuitemId(menuItemId);
        return allReviews.stream().mapToDouble(Review ::getRating).average().orElse(0.0);



    }

}
