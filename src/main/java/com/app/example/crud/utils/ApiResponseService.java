package com.app.example.crud.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseService<T> {

    private String codigo;
    private String mensaje;
    private T data;

}
