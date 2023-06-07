package com.pl.bakery.pastries;

import com.pl.bakery.ingredients.IngredientEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pastries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PastryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "pastry_ingredients",
            joinColumns = @JoinColumn(name = "pastry_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<IngredientEntity> ingredientSet = new HashSet<>();

    public PastryEntity(String name) {

        this.name = name;
    }

    public void addIngredient(IngredientEntity ingredientEntity) {
        ingredientSet.add(ingredientEntity);
    }
}
