package com.gaei.clientes.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        List<String> camposObligatorios = new ArrayList<>();
        List<String> camposInvalidos = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            if (error.getDefaultMessage().endsWith("_invalido")) {
                camposInvalidos.add(error.getDefaultMessage().replace("_invalido", ""));
            } else {
                camposObligatorios.add(error.getDefaultMessage());
            }
        }

        StringBuilder mensaje = new StringBuilder();

        if (!camposObligatorios.isEmpty()) {
            mensaje.append("Campos ")
                   .append(String.join(", ", camposObligatorios))
                   .append(". Son obligatorios.");
        }

        if (!camposInvalidos.isEmpty()) {
            if (mensaje.length() > 0) mensaje.append(" ");
            mensaje.append("Campo ")
                   .append(String.join(", ", camposInvalidos))
                   .append(", no cumple con la estructura de un correo electrónico valido.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("idTx", UUID.randomUUID().toString());
        response.put("error", mensaje.toString());

        return ResponseEntity.badRequest().body(response);
    }
}