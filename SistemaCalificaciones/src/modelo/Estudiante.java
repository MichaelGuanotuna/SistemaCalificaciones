package modelo;

public class Estudiante extends Persona {

    // Atributos
    private String codigo;
    private double[] notas;
    private boolean tieneNotas;

    // Constructor vacío
    public Estudiante() {
        super();
        this.codigo = "";
        this.notas = new double[3];
        this.tieneNotas = false;
    }

    // Constructor con parámetros
    public Estudiante(String codigo, String nombre, String apellido) {
        super(nombre, apellido);
        this.codigo = codigo;
        this.notas = new double[3];
        this.tieneNotas = false;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public double[] getNotas() {
        return notas.clone();
    }

    public boolean isTieneNotas() {
        return tieneNotas;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNotas(double[] notas) {

        if (notas == null || notas.length != 3) {
            throw new IllegalArgumentException("Debe ingresar tres notas.");
        }

        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 0 || notas[i] > 10 || Double.isNaN(notas[i])) {
                throw new IllegalArgumentException(
                    "Las notas deben estar entre 0 y 10.");
            }
        }

        this.notas = notas.clone();
        this.tieneNotas = true;
    }

    // Calcular promedio
    public double calcularPromedio() {

        if (!tieneNotas) {
            return 0;
        }

        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }

        return suma / notas.length;
    }

    // Determinar estado académico
    public String obtenerEstado() {

        if (!tieneNotas) {
            return "Sin calificar";
        }

        if (calcularPromedio() >= 7) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }

    // Representación del estudiante
    @Override
    public String toString() {
        return codigo + " - " + getNombre() + " " + getApellido();
    }
}

