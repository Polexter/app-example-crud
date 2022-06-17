package com.app.example.crud.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String id;

    @Size(max = 50)
    private String nombre;

    @Size(max = 50)
    private String apellidos;

    @Size(max = 100)
    private String direccion;

    @Size(max = 50)
    private String email;

    @Size(min = 9, max = 9)
    private String celular;

}
