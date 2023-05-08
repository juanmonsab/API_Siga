package co.edu.unisabana.otrosiga;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class Controller {

    private static final List<Estudiante> estudiantes = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody Estudiante estudiante) {
        estudiantes.add(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

}