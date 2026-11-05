package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JButton btnEstudiantes;
    private JButton btnCalificaciones;
    private JButton btnSalir;

    // Constructor
    public VentanaPrincipal() {

        setTitle("Sistema de Calificaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPane.setLayout(new BorderLayout(0, 20));

        setContentPane(contentPane);

        // Panel del título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(2, 1, 0, 5));

        JLabel lblTitulo = new JLabel("GESTIÓN ACADÉMICA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblDescripcion = new JLabel(
                "Administración de estudiantes y calificaciones");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblDescripcion);

        contentPane.add(panelTitulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 0, 15));

        btnEstudiantes = new JButton("Gestionar estudiantes");
        btnCalificaciones = new JButton("Gestionar calificaciones");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnEstudiantes);
        panelBotones.add(btnCalificaciones);
        panelBotones.add(btnSalir);

        contentPane.add(panelBotones, BorderLayout.CENTER);
    }

    // Getters de los botones
    public JButton getBtnEstudiantes() {
        return btnEstudiantes;
    }

    public JButton getBtnCalificaciones() {
        return btnCalificaciones;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
