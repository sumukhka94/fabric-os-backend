package com.sumukh.fabricos.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommunicateInfoDto {
    String channel;
    List<String> selectedCustomers;
    String templateId;
}
