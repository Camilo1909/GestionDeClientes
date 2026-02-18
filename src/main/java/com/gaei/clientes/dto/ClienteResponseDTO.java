package com.gaei.clientes.dto;

public class ClienteResponseDTO {

    private String tipoDocumento;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Integer telefono;
    private String correElectronico;

    public ClienteResponseDTO(String tipoDocumento, String numeroDocumento,
            String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido, Integer telefono, String correElectronico) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correElectronico = correElectronico;
    }

    public String getTipoDocumento() { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public String getPrimerNombre() { return primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public String getPrimerApellido() { return primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public Integer getTelefono() { return telefono; }
    public String getCorreElectronico() { return correElectronico; }
}