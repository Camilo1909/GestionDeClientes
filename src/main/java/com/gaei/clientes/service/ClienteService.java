package com.gaei.clientes.service;

import com.gaei.clientes.model.Cliente;
import com.gaei.clientes.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    // ── GUARDAR CLIENTE ──────────────────────────────────────
    public String guardarCliente(Cliente cliente) {
        String idTx = UUID.randomUUID().toString();
        logger.info("[guardarCliente] idTx: {} - Request recibido: {}", idTx, cliente.getNumeroDocumento());

        Optional<Cliente> existente = clienteRepository
            .findByTipoDocumentoAndNumeroDocumento(
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento()
            );

        if (existente.isPresent()) {
            logger.warn("[guardarCliente] idTx: {} - Cliente ya registrado: {} {}",
                idTx, cliente.getTipoDocumento(), cliente.getNumeroDocumento());
            throw new RuntimeException("YA_REGISTRADO:" + idTx);
        }

        clienteRepository.save(cliente);
        logger.info("[guardarCliente] idTx: {} - Cliente guardado exitosamente: {}", idTx, cliente.getNumeroDocumento());
        return idTx;
    }

    // ── ACTUALIZAR CLIENTE ───────────────────────────────────
    public String actualizarCliente(Cliente cliente) {
        String idTx = UUID.randomUUID().toString();
        logger.info("[actualizarCliente] idTx: {} - Request recibido: {}", idTx, cliente.getNumeroDocumento());

        Optional<Cliente> existente = clienteRepository
            .findByTipoDocumentoAndNumeroDocumento(
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento()
            );

        if (existente.isEmpty()) {
            logger.warn("[actualizarCliente] idTx: {} - Cliente no encontrado: {} {}",
                idTx, cliente.getTipoDocumento(), cliente.getNumeroDocumento());
            throw new RuntimeException("NO_ENCONTRADO:" + idTx);
        }

        Cliente clienteActualizar = existente.get();
        clienteActualizar.setPrimerNombre(cliente.getPrimerNombre());
        clienteActualizar.setSegundoNombre(cliente.getSegundoNombre());
        clienteActualizar.setPrimerApellido(cliente.getPrimerApellido());
        clienteActualizar.setSegundoApellido(cliente.getSegundoApellido());
        clienteActualizar.setTelefono(cliente.getTelefono());
        clienteActualizar.setCorreElectronico(cliente.getCorreElectronico());

        clienteRepository.save(clienteActualizar);
        logger.info("[actualizarCliente] idTx: {} - Cliente actualizado: {}", idTx, cliente.getNumeroDocumento());
        return idTx;
    }

    // ── CONSULTAR CLIENTE ────────────────────────────────────
    public Cliente consultarCliente(String tipoDocumento, String numeroDocumento) {
        logger.info("[consultarCliente] Buscando: {} {}", tipoDocumento, numeroDocumento);

        Optional<Cliente> cliente = clienteRepository
            .findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);

        if (cliente.isEmpty()) {
            logger.warn("[consultarCliente] Cliente no encontrado: {} {}", tipoDocumento, numeroDocumento);
            throw new RuntimeException("NO_ENCONTRADO");
        }

        logger.info("[consultarCliente] Cliente encontrado: {}", numeroDocumento);
        return cliente.get();
    }
}