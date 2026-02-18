# Microservicio de Gestión de Clientes — GAEI S.A

## Descripción
Microservicio REST desarrollado en Java 21 con Spring Boot 3.4.5 para el registro, consulta y actualización de clientes de la empresa financiera GAEI S.A.

## Tecnologías
- Java 21
- Spring Boot 3.4.5
- Maven 3.9.x
- Spring Data JPA
- Base de datos H2 (en memoria)
- Log4j2
- Bean Validation (Jakarta)

## Estructura del Proyecto
```
src/main/java/com/gaei/clientes/
├── controller/
│   └── ClienteController.java       # Endpoints HTTP
├── service/
│   └── ClienteService.java          # Lógica de negocio
├── repository/
│   └── ClienteRepository.java       # Acceso a base de datos
├── model/
│   └── Cliente.java                 # Entidad JPA
├── dto/
│   └── ClienteResponseDTO.java      # Objeto de respuesta
├── exception/
│   └── GlobalExceptionHandler.java  # Manejo global de errores
└── ClientesApplication.java         # Clase principal
```

## Configuración Externa
Los archivos de configuración se encuentran en `C:\config\clientes\` fuera del proyecto:
- `application.properties` — define el perfil activo
- `application-dev.properties` — configuración desarrollo
- `application-qa.properties` — configuración QA
- `application-prd.properties` — configuración producción

## Logs
Los logs se almacenan en archivo externo con ruta parametrizada por perfil:
- DEV: `C:\logs\gaei\dev\gaei-clientes.log`
- QA: `C:\logs\gaei\qa\gaei-clientes.log`
- PRD: `C:\logs\gaei\prd\gaei-clientes.log`

## Cómo Ejecutar

### Prerrequisitos
- JDK 21
- Maven 3.9.x
- Carpeta `C:\config\clientes\` con los properties
- Carpeta `C:\logs\gaei\` creada

### Arrancar el proyecto
```bash
cd clientes
mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Dspring.config.location=file:C:/config/clientes/"
```

### Cambiar perfil
Editar `C:\config\clientes\application.properties`:
```properties
spring.profiles.active=qa   # dev | qa | prd
```

## Endpoints

### 1. Guardar Cliente
```
POST /guardarCliente
Content-Type: application/json
```
**Request:**
```json
{
    "tipoDocumento": "CC",
    "numeroDocumento": "1234567890",
    "primerNombre": "Pepito",
    "segundoNombre": "Ramiro",
    "primerApellido": "Perez",
    "segundoApellido": "Gomez",
    "telefono": 12345678,
    "correElectronico": "pepito.perez@hotmail.com"
}
```
**Response exitoso (HTTP 200):**
```json
{
    "idTx": "uuid-generado",
    "mensaje": "Cliente 1234567890 almacenado de forma exitosa."
}
```
**Cliente ya registrado (HTTP 400):**
```json
{
    "idTx": "uuid-generado",
    "error": "Cliente CC 1234567890. Ya se encuentra registrado."
}
```
**Campos faltantes (HTTP 400):**
```json
{
    "idTx": "uuid-generado",
    "error": "Campos tipoDocumento, numeroDocumento. Son obligatorios."
}
```

### 2. Actualizar Cliente
```
POST /actualizarCliente
Content-Type: application/json
```
Misma estructura de request que guardar.

**Response exitoso (HTTP 200):**
```json
{
    "idTx": "uuid-generado",
    "mensaje": "Cliente 1234567890 actualizado de forma exitosa."
}
```
**Cliente no encontrado (HTTP 400):**
```json
{
    "idTx": "uuid-generado",
    "error": "Cliente CC 1234567890. No se encuentra registrado."
}
```

### 3. Consultar Cliente
```
GET /consultarCliente/{tipoDocumento}_{numeroDocumento}
```
**Ejemplo:**
```
GET /consultarCliente/CC_1234567890
```
**Response exitoso (HTTP 200):**
```json
{
    "tipoDocumento": "CC",
    "numeroDocumento": "1234567890",
    "primerNombre": "Pepito",
    "segundoNombre": "Ramiro",
    "primerApellido": "Perez",
    "segundoApellido": "Gomez",
    "telefono": 12345678,
    "correElectronico": "pepito.perez@hotmail.com"
}
```
**Cliente no encontrado (HTTP 400):**
```json
{
    "error": "Cliente CC 1234567890. No se encuentra registrado."
}
```

## Consola H2
Disponible solo en perfil DEV:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:gaeidev
Usuario: sa
Password: (vacío)
```
