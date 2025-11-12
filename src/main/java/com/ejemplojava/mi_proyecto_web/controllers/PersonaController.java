package com.ejemplojava.mi_proyecto_web.controllers;

// Importamos las clases que necesitamos
import com.ejemplojava.mi_proyecto_web.model.Persona;
import com.ejemplojava.mi_proyecto_web.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller // Le dice a Spring: "Esta clase maneja URLs"
public class PersonaController {

    // --- 1. CONEXIÓN CON EL REPOSITORIO ---
    // @Autowired es el "pegamento" que inyecta
    // la implementación de PersonaRepository que creamos.
    @Autowired
    private PersonaRepository personaRepository;


    // --- 2. MÉTODO PARA MOSTRAR LA PÁGINA ---
    // @GetMapping("/") -> "Cuando alguien visite la página principal (localhost:8080/)"
    @GetMapping("/")
    public String mostrarPaginaPrincipal(Model model) {
        
        // 1. Buscamos todas las personas guardadas en la DB
        List<Persona> personas = personaRepository.findAll();
        
        // 2. Las "pasamos" al HTML para que las pueda mostrar en la lista
        model.addAttribute("personas", personas);
        
        // 3. Pasamos un objeto "Persona" vacío (un "molde")
        //    para que el formulario HTML sepa dónde rellenar los datos.
        model.addAttribute("persona", new Persona());
        
        // 4. Devuelve el nombre del archivo HTML que debe mostrar
        return "index"; // Esto buscará "index.html" en la carpeta /templates/
    }


    // --- 3. MÉTODO PARA RECIBIR DATOS DEL FORMULARIO ---
    // @PostMapping("/guardar") -> "Cuando el formulario haga un POST a la URL /guardar"
    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute Persona persona) {
        
        // @ModelAttribute hace la magia: convierte los datos del formulario
        // (nombre, apellido, dni) en un objeto "Persona" relleno.
        
        // 1. Guardamos esa persona en la base de datos
        personaRepository.save(persona);
        
        // 2. Redirigimos al usuario de vuelta a la página principal ("/")
        //    para que vea la lista actualizada.
        return "redirect:/";
    }
}
