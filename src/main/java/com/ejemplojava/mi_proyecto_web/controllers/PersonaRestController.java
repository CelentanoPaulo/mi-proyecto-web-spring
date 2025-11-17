package com.ejemplojava.mi_proyecto_web.controllers;

import com.ejemplojava.mi_proyecto_web.model.Persona;
import com.ejemplojava.mi_proyecto_web.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Para respuestas más controladas
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones de Web

import java.util.List;
import java.util.Optional;

/*
 * 1. @RestController: ¡LA ANOTACIÓN CLAVE!
 * Le dice a Spring que esta clase NO devuelve HTML.
 * Cada método devuelve datos "crudos" (JSON) directamente.
 */
@RestController
/*
 * 2. @RequestMapping("/api/personas")
 * Establece una "ruta base". Todas las URLs de este controlador
 * empezarán con "/api/personas".
 * Ej: /api/personas, /api/personas/1, etc.
 */
@RequestMapping("/api/personas")
/*
 * 3. @CrossOrigin (¡Súper importante para el futuro!)
 * Cuando tu React corra en "localhost:3000", intentará
 * llamar a tu API en "localhost:8080". Por seguridad, los
 * navegadores bloquean esto (se llama "CORS").
 * Esta anotación le da permiso a tu app de React para que
 * pueda conectarse.
 */
@CrossOrigin(origins = "http://localhost:3000") // El puerto estándar de React
public class PersonaRestController {

    @Autowired
    private PersonaRepository personaRepository;

    // --- 1. OBTENER TODOS (READ) ---
    // GET http://localhost:8080/api/personas
    @GetMapping
    public List<Persona> obtenerTodasLasPersonas() {
        // Ya no hay "Model", ni "return 'index'".
        // Simplemente devuelves la lista. Spring la convierte a JSON.
        return personaRepository.findAllByOrderByIdAsc();
    }

    // --- 2. OBTENER UNO POR ID (READ) ---
    // GET http://localhost:8080/api/personas/1
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        
        // ResponseEntity nos permite devolver un 404 si no se encuentra
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- 3. CREAR UNA PERSONA (CREATE) ---
    // POST http://localhost:8080/api/personas
    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) {
        /*
         * @RequestBody: La anotación opuesta a @ModelAttribute.
         * En lugar de datos de formulario, le dice a Spring:
         * "Espera un JSON en el cuerpo de la petición y conviértelo
         * automáticamente a un objeto Persona".
         */
        return personaRepository.save(persona);
    }

    // --- 4. ACTUALIZAR UNA PERSONA (UPDATE) ---
    // PUT http://localhost:8080/api/personas/1
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaDetalles) {
        
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (!personaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Persona personaExistente = personaOptional.get();
        personaExistente.setNombre(personaDetalles.getNombre());
        personaExistente.setApellido(personaDetalles.getApellido());
        personaExistente.setDni(personaDetalles.getDni());
        
        Persona personaActualizada = personaRepository.save(personaExistente);
        return ResponseEntity.ok(personaActualizada);
    }


    // --- 5. ELIMINAR UNA PERSONA (DELETE) ---
    // DELETE http://localhost:8080/api/personas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        if (!personaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        personaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Devuelve un "204 No Content" (éxito)
    }
}