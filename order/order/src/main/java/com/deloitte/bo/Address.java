package com.deloitte.bo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {
    private String customerName;
    private String city;    
    private long pincode;
}
