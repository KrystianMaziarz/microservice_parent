package com.pl.bakery.bakery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BakeryRequestDto {

    private String name;
    private String location;
    private String address;
}
