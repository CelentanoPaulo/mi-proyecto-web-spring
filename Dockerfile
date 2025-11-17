# 1. Le decimos a Render que empiece con una imagen que tenga Java 21
FROM eclipse-temurin:21-jdk-jammy

# 2. Creamos una carpeta de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos solo los archivos necesarios para construir
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

# 4. Descargamos las dependencias (esto es un paso de caché, es rápido)
RUN ./mvnw dependencies

# 5. Copiamos el resto de tu código fuente (el 'src')
COPY src ./src

# 6. Construimos el proyecto (corremos "mvn clean package")
RUN ./mvnw clean package -DskipTests

# 7. Le decimos a Render cuál es el comando final para arrancar
#    (Asegúrate que el nombre del .jar coincida con tu pom.xml)
CMD ["java", "-jar", "target/mi-proyecto-web-0.0.1-SNAPSHOT.jar"]