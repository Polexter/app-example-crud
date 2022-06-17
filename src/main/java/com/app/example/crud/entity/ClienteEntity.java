package com.app.example.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_cliente")
public class ClienteEntity {

    @Id
    private String id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String celular;
    private Integer eliminado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaCreacion;

    @Column(insertable = false)
    private Date fechaActualizacion;


}
