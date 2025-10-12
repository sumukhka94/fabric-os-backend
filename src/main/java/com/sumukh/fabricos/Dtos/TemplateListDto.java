package com.sumukh.fabricos.Dtos;

import com.sumukh.fabricos.enums.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TemplateListDto {
    private String id;
    private String templateName;
    private Channel channel;
}
