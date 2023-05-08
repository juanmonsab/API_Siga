package co.edu.unisabana.otrosiga;

public class Estudiante {
    private int codigo;
    private String nombre;
    private String facultad;
    private int semestre;

    public Estudiante(int codigo, String nombre, String facultad, int semestre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.facultad = facultad;
        this.semestre = semestre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
