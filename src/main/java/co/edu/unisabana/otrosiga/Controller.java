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

    @GetMapping("/")
    public ResponseEntity<List<Estudiante>> buscarEstudiantes(@RequestParam(value = "facultad") String facultad, @RequestParam(value = "semestre", required = false) Integer semestre, @RequestParam(value = "limite", defaultValue = "0") int limite) {
        List<Estudiante> estudiantesPorFacultad = estudiantes.stream().filter(e -> e.getFacultad().equals(facultad)).collect(Collectors.toList());
        if (semestre != null) {
            estudiantesPorFacultad = estudiantesPorFacultad.stream().filter(e -> e.getSemestre() == semestre).collect(Collectors.toList());
        }
        if (limite > 0 && estudiantesPorFacultad.size() > limite) {
            estudiantesPorFacultad = estudiantesPorFacultad.subList(0, limite);
        }
        return new ResponseEntity<>(estudiantesPorFacultad, HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Estudiante> eliminarEstudiante(@PathVariable("codigo") int codigo) {
        Estudiante estudianteEliminado = null;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigo() == codigo) {
                estudianteEliminado = estudiante;
                estudiantes.remove(estudiante);
                break;
            }
        }
        if (estudianteEliminado != null) {
            return new ResponseEntity<>(estudianteEliminado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}