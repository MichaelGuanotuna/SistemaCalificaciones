
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Estudiante;
import servicio.GestorAcademico;
import vista.VentanaCalificaciones;

public class ControladorCalificaciones implements ActionListener {

    // Atributos
    private VentanaCalificaciones ventana;
    private GestorAcademico gestor;

    // Constructor
    public ControladorCalificaciones(
            VentanaCalificaciones ventana,
            GestorAcademico gestor) {

        this.ventana = ventana;
        this.gestor = gestor;

        // Registrar eventos
        ventana.getBtnGuardar().addActionListener(this);
        ventana.getBtnLimpiar().addActionListener(this);
        ventana.getBtnVolver().addActionListener(this);

        ventana.getCmbEstudiantes().addActionListener(this);

        // Cargar información inicial
        cargarEstudiantes();
        actualizarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object origen = e.getSource();

        if (origen == ventana.getBtnGuardar()) {
            guardarNotas();

        } else if (origen == ventana.getBtnLimpiar()) {
            limpiarCampos();

        } else if (origen == ventana.getBtnVolver()) {
            ventana.dispose();

        } else if (origen == ventana.getCmbEstudiantes()) {
            mostrarNotas();
        }
    }

    // Cargar estudiantes en el JComboBox
    private void cargarEstudiantes() {

        ventana.getCmbEstudiantes().removeAllItems();

        ArrayList<Estudiante> estudiantes =
                gestor.getEstudiantes();

        for (int i = 0; i < estudiantes.size(); i++) {

            ventana.getCmbEstudiantes().addItem(
                    estudiantes.get(i));
        }

        // No seleccionar estudiante inicialmente
        ventana.getCmbEstudiantes().setSelectedIndex(-1);
        limpiarCampos();
    }

    // Mostrar las notas del estudiante seleccionado
    private void mostrarNotas() {

        Estudiante estudiante = (Estudiante)
                ventana.getCmbEstudiantes().getSelectedItem();

        if (estudiante == null) {
            limpiarCampos();
            return;
        }

        if (estudiante.isTieneNotas()) {

            double[] notas = estudiante.getNotas();

            ventana.getTxtNota1().setText(
                    String.valueOf(notas[0]));

            ventana.getTxtNota2().setText(
                    String.valueOf(notas[1]));

            ventana.getTxtNota3().setText(
                    String.valueOf(notas[2]));

        } else {
            limpiarCampos();
        }
    }

    // Guardar o modificar las calificaciones
    private void guardarNotas() {

        Estudiante estudiante = (Estudiante)
                ventana.getCmbEstudiantes().getSelectedItem();

        if (estudiante == null) {

            JOptionPane.showMessageDialog(ventana,
                    "Seleccione un estudiante.");

            return;
        }

        String texto1 = ventana.getTxtNota1().getText().trim();
        String texto2 = ventana.getTxtNota2().getText().trim();
        String texto3 = ventana.getTxtNota3().getText().trim();

        if (texto1.isEmpty() || texto2.isEmpty() || texto3.isEmpty()) {

            JOptionPane.showMessageDialog(ventana,
                    "Ingrese las tres calificaciones.");

            return;
        }

        try {

            // Convertir los textos a números
            double nota1 = Double.parseDouble(texto1);
            double nota2 = Double.parseDouble(texto2);
            double nota3 = Double.parseDouble(texto3);

            // Crear arreglo de calificaciones
            double[] notas = {nota1, nota2, nota3};

            // Guardar mediante GestorAcademico
            boolean guardado = gestor.asignarNotas(
                    estudiante.getCodigo(), notas);

            if (guardado) {

                JOptionPane.showMessageDialog(ventana,
                        "Calificaciones guardadas correctamente.");

                actualizarTabla();
                cargarEstudiantes();

            } else {

                JOptionPane.showMessageDialog(ventana,
                        "No se pudieron guardar las calificaciones.");
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(ventana,
                    "Ingrese únicamente valores numéricos.");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(ventana,
                    ex.getMessage());
        }
    }

    // Mostrar las calificaciones en la JTable
    private void actualizarTabla() {

        DefaultTableModel modelo = ventana.getModeloTabla();

        // Limpiar filas anteriores
        modelo.setRowCount(0);

        ArrayList<Estudiante> estudiantes =
                gestor.getEstudiantes();

        for (int i = 0; i < estudiantes.size(); i++) {

            Estudiante estudiante = estudiantes.get(i);

            Object nota1 = "-";
            Object nota2 = "-";
            Object nota3 = "-";
            Object promedio = "-";

            if (estudiante.isTieneNotas()) {

                double[] notas = estudiante.getNotas();

                nota1 = notas[0];
                nota2 = notas[1];
                nota3 = notas[2];

                // Mostrar promedio con dos decimales
                promedio = String.format("%.2f",
                        estudiante.calcularPromedio());
            }

            Object[] fila = {
                estudiante.getCodigo(),
                estudiante.getNombre() + " "
                        + estudiante.getApellido(),
                nota1,
                nota2,
                nota3,
                promedio,
                estudiante.obtenerEstado()
            };

            modelo.addRow(fila);
        }
    }

    // Limpiar campos de notas
    private void limpiarCampos() {

        ventana.getTxtNota1().setText("");
        ventana.getTxtNota2().setText("");
        ventana.getTxtNota3().setText("");
    }
}
