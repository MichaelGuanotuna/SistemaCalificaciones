package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import servicio.GestorAcademico;
import vista.VentanaPrincipal;
import vista.VentanaEstudiantes;
import vista.VentanaCalificaciones;

public class ControladorPrincipal implements ActionListener {

    // Atributos
    private VentanaPrincipal ventana;
    private GestorAcademico gestor;

    // Constructor
    public ControladorPrincipal(VentanaPrincipal ventana,
            GestorAcademico gestor) {

        this.ventana = ventana;
        this.gestor = gestor;

        // Registrar eventos
        ventana.getBtnEstudiantes().addActionListener(this);
        ventana.getBtnCalificaciones().addActionListener(this);
        ventana.getBtnSalir().addActionListener(this);
    }

    // Eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        Object origen = e.getSource();

        if (origen == ventana.getBtnEstudiantes()) {

            abrirEstudiantes();

        } else if (origen == ventana.getBtnCalificaciones()) {

            abrirCalificaciones();

        } else if (origen == ventana.getBtnSalir()) {

            salir();
        }
    }

    // Abrir ventana de estudiantes
    private void abrirEstudiantes() {

        VentanaEstudiantes ventanaEstudiantes =
                new VentanaEstudiantes();

        new ControladorEstudiantes(ventanaEstudiantes, gestor);

        ventanaEstudiantes.setVisible(true);
    }

    // Abrir ventana de calificaciones
    private void abrirCalificaciones() {

        VentanaCalificaciones ventanaCalificaciones =
                new VentanaCalificaciones();

        new ControladorCalificaciones(ventanaCalificaciones, gestor);

        ventanaCalificaciones.setVisible(true);
    }

    // Salir del sistema
    private void salir() {

        int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Desea salir del sistema?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
