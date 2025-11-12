package com.ejemplojava.mi_proyecto_web.repository;

import com.ejemplojava.mi_proyecto_web.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Le dice a Spring: "Esto es un componente para manejar datos"
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    // JpaRepository<ClaseDelModelo, TipoDeDatoDelID>
    // JpaRepository<Persona, Long>

    // --- ¡NO NECESITAS ESCRIBIR NADA MÁS! ---
    //
    // Al extender JpaRepository, Spring automáticamente nos regala métodos como:
    // - save()         (para guardar una Persona)
    // - findAll()      (para buscar todas las Personas)
    // - findById()     (para buscar una por su ID)
    // - deleteById()   (para borrar una por su ID)
    //
}
