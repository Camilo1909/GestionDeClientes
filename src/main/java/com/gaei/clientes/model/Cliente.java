package com.gaei.clientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "tipoDocumento")
    @Column(nullable = false)
    private String tipoDocumento;

    @NotBlank(message = "numeroDocumento")
    @Column(nullable = false, unique = true)
    private String numeroDocumento;

    @NotBlank(message = "primerNombre")
    private String primerNombre;

    private String segundoNombre;

    @NotBlank(message = "primerApellido")
    private String primerApellido;

    private String segundoApellido;

    @NotNull(message = "telefono")
    private Integer telefono;

    @NotBlank(message = "correElectronico")
    @Email(message = "correElectronico_invalido")
    private String correElectronico;

    // Getters y Setters
    @JsonIgnore
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public Integer getTelefono() { return telefono; }
    public void setTelefono(Integer telefono) { this.telefono = telefono; }

    public String getCorreElectronico() { return correElectronico; }
    public void setCorreElectronico(String correElectronico) { this.correElectronico = correElectronico; }
}