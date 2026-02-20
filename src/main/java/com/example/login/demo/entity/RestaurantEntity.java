package com.example.login.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String restaurantname;
    private String location;
    @JsonManagedReference
    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)// Matches @JsonBackReference in MenuItem
    private List<MenuItem> menuItems;
   // @Size(min=5,max=10,message="description should not be more than 10 charceters")
    private String description;
    private double avgrating;
    private double latitude;
    private double longitude;
}
