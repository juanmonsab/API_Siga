package co.edu.unisabana.otrosiga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class Controller {

    private static final List<Estudiante> estudiantes = new ArrayList<>();

    @PostMapping("/crear")
    public Respuesta agregarEstudiante(@RequestBody Estudiante estudiante) {
        Random rnd = new Random();
        int codigo = 100000 + rnd.nextInt(900000); // Generar código aleatorio de 6 dígitos
        estudiante.setCodigo(codigo);
        estudiantes.add(estudiante);
        return new Respuesta("Se ha creado el estudiante correctamente");
    }

        @GetMapping("/{facultad}")
        public ResponseEntity<List<Estudiante>> buscarEstudiantes(@RequestParam(value = "facultad") String facultad) {
            List<Estudiante> estudiantesPorFacultad = estudiantes.stream().filter(e -> e.getFacultad().equalsIgnoreCase(facultad)).collect(Collectors.toList());
            return new ResponseEntity<>(estudiantesPorFacultad, HttpStatus.OK);
        }

    @GetMapping("/todos")
    public ResponseEntity<List<Estudiante>> mostrarEstudiantes() {
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{codigo}")
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

    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable("codigo") int codigo, @RequestBody Estudiante estudianteActualizado) {
        Estudiante estudianteExistente = null;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigo() == codigo) {
                estudianteExistente = estudiante;
                break;
            }
        }
        if (estudianteExistente != null) {
            estudianteExistente.setNombre(estudianteActualizado.getNombre());
            estudianteExistente.setFacultad(estudianteActualizado.getFacultad());
            estudianteExistente.setSemestre(estudianteActualizado.getSemestre());
            estudianteExistente.setPrograma(estudianteActualizado.getPrograma());
            return new ResponseEntity<>(estudianteExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}



