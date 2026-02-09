# --- ETAPA 1: CONSTRUCCIÓN (BUILD) ---
# Usamos una imagen oficial de Maven con JDK 17
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# 1. Copiamos SOLO el pom.xml primero para aprovechar la caché
COPY pom.xml .

# 2. Descargamos las dependencias ANTES de copiar el código
# Esto evita que Docker descargue internet entero si solo cambiaste una coma en tu código
RUN mvn dependency:go-offline

# 3. Copiamos el código fuente
COPY src ./src

# 4. Compilamos limitando la RAM para que Railway no "mate" el proceso
# Le damos un máximo de 512MB al proceso de Maven
ENV MAVEN_OPTS="-Xmx512m -XX:+UseSerialGC"
RUN mvn clean package -DskipTests

# --- ETAPA 2: EJECUCIÓN (RUN) ---
# Usamos una imagen muy ligera de Java 17 para correr la app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]