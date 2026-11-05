import javax.swing.SwingUtilities;

import controlador.ControladorPrincipal;
import servicio.GestorAcademico;
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Crear el gestor académico
                GestorAcademico gestor = new GestorAcademico();

                // Crear la ventana principal
                VentanaPrincipal ventana = new VentanaPrincipal();

                // Conectar la vista con su controlador
                new ControladorPrincipal(ventana, gestor);

                // Mostrar la ventana
                ventana.setVisible(true);
            }
        });
    }
}

