package com.app.example.crud.mapper;

import com.app.example.crud.entity.ClienteEntity;
import com.app.example.crud.enums.EliminadoEnum;
import com.app.example.crud.types.ClienteDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO parseClienteDTO(ClienteEntity entity){

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(entity.getId());
        clienteDTO.setNombre(entity.getNombre());
        clienteDTO.setApellidos(entity.getApellidos());
        clienteDTO.setDireccion(entity.getDireccion());
        clienteDTO.setEmail(entity.getEmail());
        clienteDTO.setCelular(entity.getCelular());
        return clienteDTO;

    }


    public List<ClienteDTO> parseClienteDTOList(List<ClienteEntity> clienteEntities){
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(clienteEntities)){
            clienteDTOS = clienteEntities.stream().map(this::parseClienteDTO)
                    .collect(Collectors.toList());
        }
        return clienteDTOS;
    }

    public ClienteEntity actualizarCliente(ClienteEntity clienteEntity, ClienteDTO clienteDTO){
        if(Objects.nonNull(clienteDTO.getNombre())){
            clienteEntity.setNombre(clienteDTO.getNombre());
        }
        if(Objects.nonNull(clienteDTO.getApellidos())){
            clienteEntity.setApellidos(clienteDTO.getApellidos());
        }
        if(Objects.nonNull(clienteDTO.getDireccion())){
            clienteEntity.setDireccion(clienteDTO.getDireccion());
        }
        if(Objects.nonNull(clienteDTO.getEmail())){
            clienteEntity.setEmail(clienteDTO.getEmail());
        }
        if(Objects.nonNull(clienteDTO.getCelular())){
            clienteEntity.setCelular(clienteDTO.getCelular());
        }
        clienteEntity.setFechaActualizacion(new Date());
        return clienteEntity;
    }

    public ClienteEntity crearClienteEntity(ClienteDTO clienteDTO){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(UUID.randomUUID().toString());
        clienteEntity.setNombre(clienteDTO.getNombre());
        clienteEntity.setApellidos(clienteDTO.getApellidos());
        clienteEntity.setDireccion(clienteDTO.getDireccion());
        clienteEntity.setEmail(clienteDTO.getEmail());
        clienteEntity.setCelular(clienteDTO.getCelular());
        clienteEntity.setFechaCreacion(new Date());
        clienteEntity.setEliminado(EliminadoEnum.NO_ELIMINADO.getValue());
        return clienteEntity;
    }

}
