package com.example.rabbitmq.two.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreatedMessage {

    private double amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    private String currency;
    private String invoiceNumber;

}
