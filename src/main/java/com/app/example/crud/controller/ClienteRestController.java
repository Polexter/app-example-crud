package com.app.example.crud.controller;

import com.app.example.crud.service.ClienteService;
import com.app.example.crud.types.ClienteDTO;
import com.app.example.crud.utils.ApiResponseError;
import com.app.example.crud.utils.ApiResponseService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente API", description = "Métodos disponibles para crud del cliente")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Este método devuelve la lista de clientes")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseService<List<ClienteDTO>> listarClientes(){
        return clienteService.listarCliente();
    }

    @Operation(summary = "Este método devuelve un cliente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseError.class))
            })
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseService<ClienteDTO> listarClientePorId(@Parameter(description = "Id único del cliente") @PathVariable("id") String id){
        return clienteService.listarClientePorId(id);
    }

    @Operation(summary = "Este método crea un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseError.class))
            })
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseService<ClienteDTO>> crearCliente(@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(clienteService.crearCliente(clienteDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Este método actualiza un cliente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseError.class))
            })
    })
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseService<ClienteDTO> actualizarCliente(@Parameter(description = "Id único del cliente") @PathVariable("id") String id,
                                                            @RequestBody ClienteDTO clienteDTO){
        clienteDTO.setId(id);
        return clienteService.actualizarCliente(clienteDTO);
    }

    @Operation(summary = "Este método elimina un cliente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseError.class))
            })
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseService<Boolean>> eliminarClientePorId(@Parameter(description = "Id único del cliente") @PathVariable("id") String id){
        return new ResponseEntity<>(clienteService.eliminarCliente(id), HttpStatus.ACCEPTED);
    }
}
