package com.app.example.crud.service.impl;

import com.app.example.crud.entity.ClienteEntity;
import com.app.example.crud.enums.EliminadoEnum;
import com.app.example.crud.enums.ResponseEnum;
import com.app.example.crud.exception.NotFoundException;
import com.app.example.crud.mapper.ClienteMapper;
import com.app.example.crud.repository.ClienteRepository;
import com.app.example.crud.service.ClienteService;
import com.app.example.crud.types.ClienteDTO;
import com.app.example.crud.utils.ApiResponseService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ApiResponseService<List<ClienteDTO>> listarCliente() {
        return Single.just(clienteRepository.findByEliminado(EliminadoEnum.NO_ELIMINADO.getValue()))
                .map(clienteEntities -> clienteMapper.parseClienteDTOList(clienteEntities))
                .map(clienteDTOS -> ApiResponseService.<List<ClienteDTO>>builder()
                        .codigo(ResponseEnum.SUCCESS.getCode())
                        .mensaje(ResponseEnum.SUCCESS.getMensaje())
                        .data(clienteDTOS)
                        .build())
                .blockingGet();
    }

    @Override
    public ApiResponseService<ClienteDTO> listarClientePorId(String id) {
        return Single.just(clienteRepository.findByIdAndEliminado(id, EliminadoEnum.NO_ELIMINADO.getValue()))
                .map(optionalClienteEntity -> optionalClienteEntity.orElseThrow(NotFoundException::new))
                .map(entity -> clienteMapper.parseClienteDTO(entity))
                .map(clienteDTO -> ApiResponseService.<ClienteDTO>builder()
                        .codigo(ResponseEnum.SUCCESS.getCode())
                        .mensaje(ResponseEnum.SUCCESS.getMensaje())
                        .data(clienteDTO)
                        .build())
                .blockingGet();
    }

    @Override
    public ApiResponseService<ClienteDTO> actualizarCliente(ClienteDTO clienteDTO) {
        return Single.just(clienteRepository.findByIdAndEliminado(clienteDTO.getId(), EliminadoEnum.NO_ELIMINADO.getValue()))
                .map(optionalClienteEntity -> optionalClienteEntity.orElseThrow(NotFoundException::new))
                .map(entity -> clienteMapper.actualizarCliente(entity, clienteDTO))
                .map(entity -> clienteRepository.saveAndFlush(entity))
                .map(entity -> clienteMapper.parseClienteDTO(entity))
                .map(dto -> ApiResponseService.<ClienteDTO>builder()
                        .codigo(ResponseEnum.SUCCESS.getCode())
                        .mensaje(ResponseEnum.SUCCESS.getMensaje())
                        .data(dto)
                        .build())
                .blockingGet();
    }

    @Override
    public ApiResponseService<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
        return Single.just(clienteMapper.crearClienteEntity(clienteDTO))
                .map(entity -> clienteRepository.saveAndFlush(entity))
                .map(entity -> clienteMapper.parseClienteDTO(entity))
                .map(dto -> ApiResponseService.<ClienteDTO>builder()
                        .codigo(ResponseEnum.SUCCESS.getCode())
                        .mensaje(ResponseEnum.SUCCESS.getMensaje())
                        .data(dto)
                        .build())
                .blockingGet();
    }

    @Override
    public ApiResponseService<Boolean> eliminarCliente(String id) {
        return Single.just(clienteRepository.findByIdAndEliminado(id, EliminadoEnum.NO_ELIMINADO.getValue()))
                .map(optionalClienteEntity -> optionalClienteEntity.orElseThrow(NotFoundException::new))
                .map(this::eliminarCliente)
                .map(entity -> clienteRepository.saveAndFlush(entity))
                .map(clienteEntity -> ApiResponseService.<Boolean>builder()
                        .codigo(ResponseEnum.SUCCESS.getCode())
                        .mensaje(ResponseEnum.SUCCESS.getMensaje())
                        .data(Boolean.TRUE)
                        .build())
                .blockingGet();
    }

    private ClienteEntity eliminarCliente(ClienteEntity entity){
        entity.setEliminado(EliminadoEnum.ELIMINADO.getValue());
        entity.setFechaActualizacion(new Date());
        return entity;
    }
}
