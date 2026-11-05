
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Estudiante;
import servicio.GestorAcademico;
import vista.VentanaEstudiantes;

public class ControladorEstudiantes implements ActionListener, ListSelectionListener {

    // Atributos
    private VentanaEstudiantes ventana;
    private GestorAcademico gestor;

    // Constructor
    public ControladorEstudiantes(VentanaEstudiantes ventana,
            GestorAcademico gestor) {

        this.ventana = ventana;
        this.gestor = gestor;

        // Eventos de los botones
        ventana.getBtnRegistrar().addActionListener(this);
        ventana.getBtnModificar().addActionListener(this);
        ventana.getBtnEliminar().addActionListener(this);
        ventana.getBtnBuscar().addActionListener(this);
        ventana.getBtnLimpiar().addActionListener(this);
        ventana.getBtnVolver().addActionListener(this);

        // Evento de selección de la tabla
        ventana.getTablaEstudiantes().getSelectionModel()
                .addListSelectionListener(this);

        // Mostrar estudiantes al abrir la ventana
        actualizarTabla();
    }

    // Eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        Object origen = e.getSource();

        if (origen == ventana.getBtnRegistrar()) {
            registrar();

        } else if (origen == ventana.getBtnModificar()) {
            modificar();

        } else if (origen == ventana.getBtnEliminar()) {
            eliminar();

        } else if (origen == ventana.getBtnBuscar()) {
            buscar();

        } else if (origen == ventana.getBtnLimpiar()) {
            limpiarCampos();

        } else if (origen == ventana.getBtnVolver()) {
            ventana.dispose();
        }
    }

    // Registrar estudiante
    private void registrar() {

        String codigo = ventana.getTxtCodigo().getText().trim();
        String nombre = ventana.getTxtNombre().getText().trim();
        String apellido = ventana.getTxtApellido().getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Complete todos los campos.");
            return;
        }

        if (gestor.buscar(codigo) != null) {
            JOptionPane.showMessageDialog(ventana,
                    "Ya existe un estudiante con ese código.");
            return;
        }

        Estudiante estudiante = new Estudiante(codigo, nombre, apellido);

        if (gestor.registrar(estudiante)) {

            JOptionPane.showMessageDialog(ventana,
                    "Estudiante registrado correctamente.");

            actualizarTabla();
            limpiarCampos();

        } else {
            JOptionPane.showMessageDialog(ventana,
                    "No se pudo registrar. Revise los datos o el archivo.");
        }
    }

    // Buscar estudiante
    private void buscar() {

        String codigo = ventana.getTxtCodigo().getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Ingrese el código del estudiante.");
            return;
        }

        Estudiante estudiante = gestor.buscar(codigo);

        if (estudiante != null) {

            ventana.getTxtNombre().setText(estudiante.getNombre());
            ventana.getTxtApellido().setText(estudiante.getApellido());

        } else {
            JOptionPane.showMessageDialog(ventana,
                    "Estudiante no encontrado.");
        }
    }

    // Modificar estudiante
    private void modificar() {

        String codigo = ventana.getTxtCodigo().getText().trim();
        String nombre = ventana.getTxtNombre().getText().trim();
        String apellido = ventana.getTxtApellido().getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Complete todos los campos.");
            return;
        }

        if (gestor.modificar(codigo, nombre, apellido)) {

            JOptionPane.showMessageDialog(ventana,
                    "Estudiante modificado correctamente.");

            actualizarTabla();
            limpiarCampos();

        } else {
            JOptionPane.showMessageDialog(ventana,
                    "No se pudo modificar. Revise el código y los datos.");
        }
    }

    // Eliminar estudiante
    private void eliminar() {

        String codigo = ventana.getTxtCodigo().getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Seleccione o busque un estudiante.");
            return;
        }

        if (gestor.buscar(codigo) == null) {
            JOptionPane.showMessageDialog(ventana,
                    "El estudiante no existe.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Desea eliminar al estudiante " + codigo + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            if (gestor.eliminar(codigo)) {

                JOptionPane.showMessageDialog(ventana,
                        "Estudiante eliminado correctamente.");

                actualizarTabla();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(ventana,
                        "No se pudo eliminar el estudiante.");
            }
        }
    }

    // Actualizar los datos de la JTable
    private void actualizarTabla() {

        DefaultTableModel modelo = ventana.getModeloTabla();

        // Eliminar las filas anteriores
        modelo.setRowCount(0);

        ArrayList<Estudiante> estudiantes = gestor.getEstudiantes();

        for (int i = 0; i < estudiantes.size(); i++) {

            Estudiante estudiante = estudiantes.get(i);

            Object[] fila = {
                estudiante.getCodigo(),
                estudiante.getNombre(),
                estudiante.getApellido()
            };

            modelo.addRow(fila);
        }
    }

    // Limpiar los campos del formulario
    private void limpiarCampos() {

        ventana.getTxtCodigo().setText("");
        ventana.getTxtNombre().setText("");
        ventana.getTxtApellido().setText("");

        ventana.getTablaEstudiantes().clearSelection();
        ventana.getTxtCodigo().requestFocus();
    }

    // Evento de selección de la tabla
    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) {
            return;
        }

        int fila = ventana.getTablaEstudiantes().getSelectedRow();

        if (fila >= 0) {

            int filaModelo = ventana.getTablaEstudiantes()
                    .convertRowIndexToModel(fila);

            DefaultTableModel modelo = ventana.getModeloTabla();

            String codigo = modelo.getValueAt(filaModelo, 0).toString();
            String nombre = modelo.getValueAt(filaModelo, 1).toString();
            String apellido = modelo.getValueAt(filaModelo, 2).toString();

            ventana.getTxtCodigo().setText(codigo);
            ventana.getTxtNombre().setText(nombre);
            ventana.getTxtApellido().setText(apellido);
        }
    }
}
