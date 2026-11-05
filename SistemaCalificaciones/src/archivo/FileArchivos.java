package archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Estudiante;

public class FileArchivos {

    // Carpeta y archivo donde se guardan los estudiantes
    private String carpeta = "datos";
    private String ruta = "datos/estudiantes.txt";

    // Guardar los estudiantes en el archivo
    public boolean guardar(ArrayList<Estudiante> estudiantes) {

        File directorio = new File(carpeta);

        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("No se pudo crear la carpeta.");
                return false;
            }
        }

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(ruta))) {

            for (int i = 0; i < estudiantes.size(); i++) {

                Estudiante e = estudiantes.get(i);

                String linea = e.getCodigo() + "|"
                        + e.getNombre() + "|"
                        + e.getApellido();

                if (e.isTieneNotas()) {

                    double[] notas = e.getNotas();

                    for (int j = 0; j < notas.length; j++) {
                        linea += "|" + notas[j];
                    }

                } else {
                    linea += "|||";
                }

                escritor.write(linea);
                escritor.newLine();
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    // Cargar los estudiantes desde el archivo
    public ArrayList<Estudiante> cargar() {

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return estudiantes;
        }

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                try {
                    String[] datos = linea.split("\\|", -1);

                    if (datos.length != 6) {
                        System.out.println("Registro incompleto.");
                        continue;
                    }

                    Estudiante estudiante = new Estudiante(
                            datos[0], datos[1], datos[2]);

                    // Comprobar si tiene calificaciones
                    if (!datos[3].isEmpty()
                            && !datos[4].isEmpty()
                            && !datos[5].isEmpty()) {

                        double[] notas = new double[3];

                        for (int i = 0; i < 3; i++) {
                            notas[i] = Double.parseDouble(datos[i + 3]);
                        }

                        estudiante.setNotas(notas);
                    }

                    estudiantes.add(estudiante);

                } catch (NumberFormatException e) {
                    System.out.println("Error en las notas: "
                            + e.getMessage());

                } catch (IllegalArgumentException e) {
                    System.out.println("Datos inválidos: "
                            + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }

        return estudiantes;
    }
}
