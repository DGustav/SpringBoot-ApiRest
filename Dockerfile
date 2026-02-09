# Usamos una imagen de Java para compilar (Maven + JDK 17)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# --- AGREGA ESTA LÍNEA AQUÍ ---
# Limitamos Maven a 256MB o 512MB de RAM para que no explote
ENV MAVEN_OPTS="-Xmx512m -XX:+UseSerialGC"

COPY . .
RUN mvn clean package -DskipTests

# Usamos una imagen ligera para ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]