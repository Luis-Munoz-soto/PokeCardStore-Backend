# Usar imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml y código
COPY pom.xml .
COPY src ./src

# Construir el proyecto
RUN ./mvnw package -DskipTests

# Exponer puerto
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "target/pokecardstore-0.0.1-SNAPSHOT.jar"]
