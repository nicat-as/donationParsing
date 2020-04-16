package com.donation.domain.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {
    private String status;
    private T data;
    private String error;
}
