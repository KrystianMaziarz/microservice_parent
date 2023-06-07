package com.pl.bakery.ingredients;

import com.pl.bakery.pastries.PastryEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    @ManyToMany(mappedBy = "ingredientSet")
    private Set<PastryEntity> pastriesSet = new HashSet<>();

    public IngredientEntity(String name) {
        this.name = name;
    }

    public void addPastry(PastryEntity pastryEntity) {
        pastriesSet.add(pastryEntity);
    }
}
