package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Estudiante;

public class VentanaCalificaciones extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Componentes del formulario
    private JComboBox<Estudiante> cmbEstudiantes;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;

    // Botones
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    // Tabla
    private JTable tablaCalificaciones;
    private DefaultTableModel modeloTabla;

    // Constructor
    public VentanaCalificaciones() {

        setTitle("Gestión de Calificaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPane.setLayout(new BorderLayout(0, 15));

        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("REGISTRO DE CALIFICACIONES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout(0, 15));

        contentPane.add(panelCentral, BorderLayout.CENTER);

        // Panel del formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblEstudiante = new JLabel("Estudiante:");
        cmbEstudiantes = new JComboBox<Estudiante>();

        JLabel lblNota1 = new JLabel("Nota 1:");
        txtNota1 = new JTextField();

        JLabel lblNota2 = new JLabel("Nota 2:");
        txtNota2 = new JTextField();

        JLabel lblNota3 = new JLabel("Nota 3:");
        txtNota3 = new JTextField();

        panelFormulario.add(lblEstudiante);
        panelFormulario.add(cmbEstudiantes);

        panelFormulario.add(lblNota1);
        panelFormulario.add(txtNota1);

        panelFormulario.add(lblNota2);
        panelFormulario.add(txtNota2);

        panelFormulario.add(lblNota3);
        panelFormulario.add(txtNota3);

        panelCentral.add(panelFormulario, BorderLayout.NORTH);

        // Modelo de la tabla
        String[] columnas = {
            "Código", "Estudiante",
            "Nota 1", "Nota 2", "Nota 3",
            "Promedio", "Estado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaCalificaciones = new JTable(modeloTabla);
        tablaCalificaciones.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tablaCalificaciones);

        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 10));

        btnGuardar = new JButton("Guardar notas");
        btnLimpiar = new JButton("Limpiar");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);

        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    // Getters de los componentes
    public JComboBox<Estudiante> getCmbEstudiantes() {
        return cmbEstudiantes;
    }

    public JTextField getTxtNota1() {
        return txtNota1;
    }

    public JTextField getTxtNota2() {
        return txtNota2;
    }

    public JTextField getTxtNota3() {
        return txtNota3;
    }

    // Getters de los botones
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    // Getters de la tabla
    public JTable getTablaCalificaciones() {
        return tablaCalificaciones;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
