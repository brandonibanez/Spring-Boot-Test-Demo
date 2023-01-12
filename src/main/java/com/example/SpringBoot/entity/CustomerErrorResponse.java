package com.example.SpringBoot.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomerErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
