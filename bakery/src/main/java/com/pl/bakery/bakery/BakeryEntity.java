package com.pl.bakery.bakery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bakery")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BakeryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String location;

    public BakeryEntity(String name, String address, String location) {
        this.name = name;
        this.address = address;
        this.location = location;
    }
}
