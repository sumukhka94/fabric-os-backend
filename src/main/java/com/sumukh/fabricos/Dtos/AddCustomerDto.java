package com.sumukh.fabricos.Dtos;

import lombok.Data;

@Data
public class AddCustomerDto {
    private String name;
    private String email;
    private String phone;
    private String address;
}
