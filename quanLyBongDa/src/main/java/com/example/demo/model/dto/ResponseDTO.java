package com.example.demo.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseDTO {
    private Object data;
    private int code;
    private String message;
}
