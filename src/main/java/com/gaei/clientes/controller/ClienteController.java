package com.gaei.clientes.controller;

import com.gaei.clientes.dto.ClienteResponseDTO;
import com.gaei.clientes.model.Cliente;
import com.gaei.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    // ── GUARDAR CLIENTE ──────────────────────────────────────
    @PostMapping("/guardarCliente")
    public ResponseEntity<Map<String, String>> guardarCliente(
            @Valid @RequestBody Cliente cliente) {

        logger.info("[guardarCliente] Iniciando para: {}", cliente.getNumeroDocumento());

        try {
            String idTx = clienteService.guardarCliente(cliente);
            Map<String, String> response = new HashMap<>();
            response.put("idTx", idTx);
            response.put("mensaje", "Cliente " + cliente.getNumeroDocumento() + " almacenado de forma exitosa.");
            logger.info("[guardarCliente] Response exitoso idTx: {}", idTx);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            String[] partes = e.getMessage().split(":");
            String idTx = partes.length > 1 ? partes[1] : "";
            Map<String, String> error = new HashMap<>();
            error.put("idTx", idTx);
            error.put("error", "Cliente " + cliente.getTipoDocumento() + " " +
                cliente.getNumeroDocumento() + ". Ya se encuentra registrado.");
            logger.error("[guardarCliente] Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // ── ACTUALIZAR CLIENTE ───────────────────────────────────
    @PostMapping("/actualizarCliente")
    public ResponseEntity<Map<String, String>> actualizarCliente(
            @Valid @RequestBody Cliente cliente) {

        logger.info("[actualizarCliente] Iniciando para: {}", cliente.getNumeroDocumento());

        try {
            String idTx = clienteService.actualizarCliente(cliente);
            Map<String, String> response = new HashMap<>();
            response.put("idTx", idTx);
            response.put("mensaje", "Cliente " + cliente.getNumeroDocumento() + " actualizado de forma exitosa.");
            logger.info("[actualizarCliente] Response exitoso idTx: {}", idTx);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            String[] partes = e.getMessage().split(":");
            String idTx = partes.length > 1 ? partes[1] : "";
            Map<String, String> error = new HashMap<>();
            error.put("idTx", idTx);
            error.put("error", "Cliente " + cliente.getTipoDocumento() + " " +
                cliente.getNumeroDocumento() + ". No se encuentra registrado.");
            logger.error("[actualizarCliente] Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // ── CONSULTAR CLIENTE ────────────────────────────────────
    @GetMapping("/consultarCliente/{identificador}")
    public ResponseEntity<?> consultarCliente(
            @PathVariable String identificador) {

        logger.info("[consultarCliente] Buscando: {}", identificador);

        try {
            String[] partes = identificador.split("_");
            String tipoDocumento = partes[0];
            String numeroDocumento = partes[1];

            Cliente cliente = clienteService.consultarCliente(tipoDocumento, numeroDocumento);

            ClienteResponseDTO response = new ClienteResponseDTO(
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento(),
                cliente.getPrimerNombre(),
                cliente.getSegundoNombre(),
                cliente.getPrimerApellido(),
                cliente.getSegundoApellido(),
                cliente.getTelefono(),
                cliente.getCorreElectronico()
            );

            logger.info("[consultarCliente] Cliente encontrado: {}", numeroDocumento);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            String[] partes = identificador.split("_");
            Map<String, String> error = new HashMap<>();
            error.put("error", "Cliente " + partes[0] + " " + partes[1] + ". No se encuentra registrado.");
            logger.error("[consultarCliente] Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}