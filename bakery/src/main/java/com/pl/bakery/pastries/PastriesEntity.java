package com.pl.bakery.pastries;

import com.pl.bakery.ingredients.IngredientEntity;
import jakarta.persistence.*;
import java.util.List;
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
public class PastriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany private List<IngredientEntity> ingredientsList;
}
