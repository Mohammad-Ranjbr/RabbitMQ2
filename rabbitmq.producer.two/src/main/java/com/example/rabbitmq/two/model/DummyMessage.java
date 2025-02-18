package com.example.rabbitmq.two.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DummyMessage {

    private String content;
    private int publishOrder;

}
