
package servicio;

import java.util.ArrayList;

import archivo.FileArchivos;
import generico.UtilGenerico;
import modelo.Estudiante;

public class GestorAcademico {

    // Atributos
    private ArrayList<Estudiante> estudiantes;
    private FileArchivos archivos;
    private UtilGenerico<Estudiante> utilidad;

    // Constructor
    public GestorAcademico() {

        archivos = new FileArchivos();
        utilidad = new UtilGenerico<>();

        // Recuperar los estudiantes guardados
        estudiantes = archivos.cargar();
    }

    // Registrar estudiante
    public boolean registrar(Estudiante estudiante) {

        if (estudiante == null) {
            return false;
        }

        if (!datosValidos(estudiante.getCodigo(),
                estudiante.getNombre(), estudiante.getApellido())) {
            return false;
        }

        if (buscar(estudiante.getCodigo()) != null) {
            return false;
        }

        ArrayList<Estudiante> nuevaLista =
                new ArrayList<>(estudiantes);

        utilidad.agregarVarios(nuevaLista, estudiante);

        if (archivos.guardar(nuevaLista)) {
            estudiantes = nuevaLista;
            return true;
        }

        return false;
    }

    // Buscar estudiante por código
    public Estudiante buscar(String codigo) {

        if (codigo == null) {
            return null;
        }

        for (int i = 0; i < estudiantes.size(); i++) {

            Estudiante e = estudiantes.get(i);

            if (e.getCodigo().equals(codigo.trim())) {
                return e;
            }
        }

        return null;
    }

    // Modificar los datos de un estudiante
    public boolean modificar(String codigo,
            String nombre, String apellido) {

        if (!datosValidos(codigo, nombre, apellido)) {
            return false;
        }

        Estudiante actual = buscar(codigo);

        if (actual == null) {
            return false;
        }

        Estudiante modificado = new Estudiante(
                actual.getCodigo(),
                nombre.trim(),
                apellido.trim());

        // Conservar las notas anteriores
        if (actual.isTieneNotas()) {
            modificado.setNotas(actual.getNotas());
        }

        ArrayList<Estudiante> nuevaLista =
                new ArrayList<>(estudiantes);

        int posicion = estudiantes.indexOf(actual);
        nuevaLista.set(posicion, modificado);

        if (archivos.guardar(nuevaLista)) {
            estudiantes = nuevaLista;
            return true;
        }

        return false;
    }

    // Eliminar estudiante
    public boolean eliminar(String codigo) {

        Estudiante estudiante = buscar(codigo);

        if (estudiante == null) {
            return false;
        }

        ArrayList<Estudiante> nuevaLista =
                new ArrayList<>(estudiantes);

        nuevaLista.remove(estudiante);

        if (archivos.guardar(nuevaLista)) {
            estudiantes = nuevaLista;
            return true;
        }

        return false;
    }

    // Asignar o actualizar las tres notas
    public boolean asignarNotas(String codigo, double[] notas) {

        Estudiante actual = buscar(codigo);

        if (actual == null) {
            return false;
        }

        Estudiante actualizado = new Estudiante(
                actual.getCodigo(),
                actual.getNombre(),
                actual.getApellido());

        // La clase Estudiante valida las notas
        actualizado.setNotas(notas);

        ArrayList<Estudiante> nuevaLista =
                new ArrayList<>(estudiantes);

        int posicion = estudiantes.indexOf(actual);
        nuevaLista.set(posicion, actualizado);

        if (archivos.guardar(nuevaLista)) {
            estudiantes = nuevaLista;
            return true;
        }

        return false;
    }

    // Obtener lista de estudiantes
    public ArrayList<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    // Obtener cantidad de estudiantes
    public int contarEstudiantes() {
        return estudiantes.size();
    }

    // Validar campos de texto
    private boolean datosValidos(String codigo,
            String nombre, String apellido) {

        if (codigo == null || nombre == null || apellido == null) {
            return false;
        }

        if (codigo.trim().isEmpty()
                || nombre.trim().isEmpty()
                || apellido.trim().isEmpty()) {
            return false;
        }

        String texto = codigo + nombre + apellido;

        if (texto.contains("|")
                || texto.contains("\n")
                || texto.contains("\r")) {
            return false;
        }

        return true;
    }
}
