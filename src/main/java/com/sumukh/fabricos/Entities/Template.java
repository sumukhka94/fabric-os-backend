package com.sumukh.fabricos.Entities;

import com.sumukh.fabricos.enums.Channel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String templateName;
    private Channel channel;

    @Lob
    private String subject;
}
