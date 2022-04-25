package com.deloitte.bo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {
    private long id;
    private String name;
    private double price;
}
