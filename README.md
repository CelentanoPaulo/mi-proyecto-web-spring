# Sistema de Gestión de Personas (Fullstack con Spring Boot)

##  Sobre el Proyecto

Este proyecto es una aplicación web **Fullstack** desarrollada como parte de mi proceso de aprendizaje autodidacta en el ecosistema de **Java** y **Spring Boot**.

El objetivo principal de este desarrollo no fue solo escribir código, sino **entender la arquitectura** completa de una aplicación web moderna: cómo viajan los datos desde un formulario en el navegador, cómo son procesados por el servidor, y cómo se persisten finalmente en una base de datos relacional.

Este proyecto representa mi "mapa" para ubicarme en el desarrollo backend, conectando conceptos como MVC, inyección de dependencias y mapeo objeto-relacional (ORM).

##  Arquitectura del Proyecto

La aplicación sigue el patrón de arquitectura **MVC (Modelo - Vista - Controlador)**, estructurado en capas para separar responsabilidades:

1.  **Capa de Vista (Frontend):**
    * Utiliza **HTML5** y **Thymeleaf** para renderizar las páginas dinámicamente desde el servidor.
    * Estilizado con **CSS3** personalizado para una interfaz limpia y moderna.

2.  **Capa de Controladores (Backend - Spring MVC):**
    * Recibe las peticiones HTTP (GET y POST) del usuario.
    * Actúa como un intermediario conectando la vista con la capa de datos.

3.  **Capa de Modelo (Datos):**
    * Clases Java anotadas con **JPA** (`@Entity`) que representan la estructura de los datos (Persona).

4.  **Capa de Persistencia (Base de Datos):**
    * Uso de **Spring Data JPA** (`Repository`) para abstraer las consultas SQL.
    * Conexión a **PostgreSQL** para el almacenamiento permanente.

### Flujo de Datos
`Usuario (Navegador)` ↔ `Controlador (Spring)` ↔ `Repositorio (JPA)` ↔ `Base de Datos (PostgreSQL)`

##  Tecnologías Usadas

* **Lenguaje:** Java 21
* **Framework Principal:** Spring Boot 3.x
* **Gestor de Dependencias:** Maven
* **Base de Datos:** PostgreSQL
* **ORM:** Hibernate (vía Spring Data JPA)
* **Motor de Plantillas:** Thymeleaf
* **Estilos:** CSS nativo

##  Instalación y Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/CelentanoPaulo/gestion-persona-java-springboot.git
    ```
2.  **Configurar la Base de Datos:**
    * Tener PostgreSQL instalado y corriendo.
    * Crear una base de datos (o usar `postgres`).
    * Configurar las credenciales en `src/main/resources/application.properties`.

3.  **Ejecutar la aplicación:**
    * Desde tu IDE (VSCode/IntelliJ) ejecutando la clase principal.
    * O vía terminal: `mvn spring-boot:run`

4.  **Acceder:**
    * Abrir `http://localhost:8080` en el navegador.

##  Próximos Pasos y Aprendizaje

Soy consciente de que esto es solo el comienzo de un camino largo y emocionante en el desarrollo de software. He logrado conectar las piezas básicas, pero mis próximos objetivos de aprendizaje incluyen:

* [ ] Implementar las funciones de **Eliminar** y **Editar** (completar el CRUD).
* [ ] Agregar validaciones de datos (ej. no permitir DNI duplicados).
* [ ] Separar el Backend del Frontend creando una **API REST** (`@RestController`).
* [ ] Aprender sobre seguridad con **Spring Security**.
* [ ] Desplegar la aplicación en un servidor real.
