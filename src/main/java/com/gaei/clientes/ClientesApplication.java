package com.gaei.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientesApplication {

    public static void main(String[] args) {
        // Carga el contexto primero para leer el perfil activo
        ConfigurableApplicationContext context = 
            SpringApplication.run(ClientesApplication.class, args);
        
        // Lee log.path del properties y lo pasa como variable del sistema
        String logPath = context.getEnvironment().getProperty("log.path");
        System.setProperty("LOG_PATH", logPath);
    }
}