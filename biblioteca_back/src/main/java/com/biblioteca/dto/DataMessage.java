package com.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataMessage {
    private String bookId;
    private String userId;
    private String dataType;
    private Object value;
}
