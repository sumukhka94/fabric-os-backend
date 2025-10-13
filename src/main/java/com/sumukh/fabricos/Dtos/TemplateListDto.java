package com.sumukh.fabricos.Dtos;

import com.sumukh.fabricos.enums.Channel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TemplateListDto {
    private String id;
    private String templateName;
    private Channel channel;
}
