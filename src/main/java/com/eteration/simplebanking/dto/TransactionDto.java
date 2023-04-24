package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {

    private LocalDateTime date;
    private Double amount;
    private String type;
    private UUID approvalCode;
}
