# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu proyecto al contenedor
COPY target/animalshelter.jar /app/animalshelter.jar

# Expone el puerto en el que tu aplicación estará corriendo
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/animalshelter.jar"]
