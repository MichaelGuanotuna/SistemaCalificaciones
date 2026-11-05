
package modelo;

public class Persona {

    // Atributos
    private String nombre;
    private String apellido;

    // Constructor vacío
    public Persona() {
        this.nombre = "";
        this.apellido = "";
    }

    // Constructor con parámetros
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
