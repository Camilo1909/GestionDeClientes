@echo off
echo ============================================
echo   GAEI S.A - Microservicio de Clientes
echo ============================================
echo.
echo Iniciando servicio...
echo Perfil activo: Ver C:\config\clientes\application.properties
echo Logs en:       C:\logs\gaei\
echo.
mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Dspring.config.location=file:C:/config/clientes/"