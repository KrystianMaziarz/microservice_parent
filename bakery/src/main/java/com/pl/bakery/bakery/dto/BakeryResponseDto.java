package com.pl.bakery.bakery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BakeryResponseDto {

    private Long id;

    private String name;

    private String location;
}
