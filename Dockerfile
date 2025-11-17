# 1. Le decimos a Render que empiece con una imagen que tenga Java 21
FROM eclipse-temurin:21-jdk-jammy

# 2. Creamos una carpeta de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos solo los archivos necesarios para construir
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 4. Damos permisos de ejecución (ESTO ESTÁ BIEN)
RUN chmod +x ./mvnw

# 5. Copiamos el resto de tu código fuente (el 'src')
COPY src ./src

# 6. Construimos el proyecto (corremos "mvn clean package")
#    (Esta línea YA DESCARGA las dependencias)
RUN ./mvnw clean package -DskipTests

# 7. Le decimos a Render cuál es el comando final para arrancar
CMD ["java", "-jar", "target/mi-proyecto-web-0.0.1-SNAPSHOT.jar"]