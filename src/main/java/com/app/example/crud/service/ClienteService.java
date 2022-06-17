package com.app.example.crud.service;

import com.app.example.crud.types.ClienteDTO;
import com.app.example.crud.utils.ApiResponseService;

import java.util.List;

public interface ClienteService {

    ApiResponseService<List<ClienteDTO>> listarCliente();

    ApiResponseService<ClienteDTO> listarClientePorId(String id);

    ApiResponseService<ClienteDTO> actualizarCliente(ClienteDTO clienteDTO);

    ApiResponseService<ClienteDTO> crearCliente(ClienteDTO clienteDTO);

    ApiResponseService<Boolean> eliminarCliente(String id);

}
