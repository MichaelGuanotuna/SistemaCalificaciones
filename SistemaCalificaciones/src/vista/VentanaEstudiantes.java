package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaEstudiantes extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Campos de texto
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;

    // Botones
    private JButton btnRegistrar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    // Tabla
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;

    // Constructor
    public VentanaEstudiantes() {

        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPane.setLayout(new BorderLayout(0, 15));

        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE ESTUDIANTES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout(0, 15));

        contentPane.add(panelCentral, BorderLayout.CENTER);

        // Formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();

        panelFormulario.add(lblCodigo);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);

        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);

        panelCentral.add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Código", "Nombre", "Apellido"};

        modeloTabla = new DefaultTableModel(columnas, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaEstudiantes = new JTable(modeloTabla);
        tablaEstudiantes.setRowHeight(25);
        tablaEstudiantes.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);

        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 3, 10, 10));

        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);

        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    // Getters de los campos
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    // Getters de los botones
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    // Getters de la tabla
    public JTable getTablaEstudiantes() {
        return tablaEstudiantes;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
