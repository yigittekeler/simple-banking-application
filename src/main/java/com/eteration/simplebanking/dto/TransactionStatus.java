package com.eteration.simplebanking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionStatus {

    private String status;
    private UUID approvalCode;
}
