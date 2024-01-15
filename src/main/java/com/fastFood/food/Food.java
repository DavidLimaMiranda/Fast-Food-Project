package com.fastFood.food;

import com.fastFood.dtos.FoodDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "FastFood")
@Table(name = "fast_food")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private int stock;
    @Column(name = "price")
    private BigDecimal price;

    public Food(FoodDTO foodDTO) {

        this.foodName = foodDTO.foodName();
        this.description = foodDTO.description();
        this.stock = foodDTO.stock();
        this.price = foodDTO.price();
    }
}
