package com.example.rabbitmq.two.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCancelledMessage {

    private String reason;
    private String invoiceNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelDate;

}
