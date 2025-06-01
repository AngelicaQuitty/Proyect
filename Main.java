import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.HashMap;

class SistemaGestionAlimentos extends JFrame {
    private ArrayList<Donador> donadores = new ArrayList<>();
    private JTextField txtNombreDonador, txtContactoDonador;
    private JTextField txtNombreAlimento, txtCantidadAlimento;
    private JTextArea areaDatos;
    private JComboBox<Donador> comboDonadores;
    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SistemaGestionAlimentos() {
        setTitle("Sistema de Gestión de Alimentos Donados");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(233, 243, 227));

        JLabel titulo = new JLabel(" FoodShare", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(38, 108, 38));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelFormularios = new JPanel();
        panelFormularios.setLayout(new BoxLayout(panelFormularios, BoxLayout.Y_AXIS));
        panelFormularios.setBackground(new Color(241, 253, 253));

        // Panel para registrar donadores
        JPanel panelDonador = new JPanel(new GridLayout(3, 1, 5, 5));
        panelDonador.setBorder(BorderFactory.createTitledBorder("Registrar Donador"));
        panelDonador.setBackground(new Color(208, 239, 223));
        txtNombreDonador = new JTextField();
        txtNombreDonador.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtNombreDonador.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelDonador.add(txtNombreDonador);

        txtContactoDonador = new JTextField();
        txtContactoDonador.setBorder(BorderFactory.createTitledBorder("Contacto"));
        panelDonador.add(txtContactoDonador);

        JButton btnGuardarDonador = new JButton("Guardar Donador");
        btnGuardarDonador.setBackground(new Color(96, 207, 174));
        btnGuardarDonador.setForeground(Color.BLACK);
        btnGuardarDonador.setFocusPainted(false);
        btnGuardarDonador.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelDonador.add(btnGuardarDonador);

        // Panel para registrar alimentos
        JPanel panelAlimento = new JPanel(new GridLayout(6, 1, 5, 5));
        panelAlimento.setBorder(BorderFactory.createTitledBorder("Registrar Alimento"));
        panelAlimento.setBackground(new Color(208, 239, 223));

        comboDonadores = new JComboBox<>();
        comboDonadores.setBorder(BorderFactory.createTitledBorder("Donador"));
        panelAlimento.add(comboDonadores);

        txtNombreAlimento = new JTextField();
        txtNombreAlimento.setBorder(BorderFactory.createTitledBorder("Nombre"));
        panelAlimento.add(txtNombreAlimento);

        txtCantidadAlimento = new JTextField();
        txtCantidadAlimento.setBorder(BorderFactory.createTitledBorder("Cantidad"));
        panelAlimento.add(txtCantidadAlimento);

        JPanel panelFecha = new JPanel(new GridLayout(1, 3, 5, 5));
        panelFecha.setBorder(BorderFactory.createTitledBorder("Fecha de Vencimiento"));
        panelFecha.setBackground(new Color(208, 239, 223));

        JComboBox<Integer> comboAnio = new JComboBox<>();
        JComboBox<Integer> comboMes = new JComboBox<>();
        JComboBox<Integer> comboDia = new JComboBox<>();

        for (int i = 2020; i <= 2030; i++) comboAnio.addItem(i);
        for (int i = 1; i <= 12; i++) comboMes.addItem(i);
        for (int i = 1; i <= 31; i++) comboDia.addItem(i);

        comboAnio.setBorder(BorderFactory.createTitledBorder("Año"));
        comboMes.setBorder(BorderFactory.createTitledBorder("Mes"));
        comboDia.setBorder(BorderFactory.createTitledBorder("Día"));

        panelFecha.add(comboAnio);
        panelFecha.add(comboMes);
        panelFecha.add(comboDia);

        panelAlimento.add(panelFecha);

        JButton btnGuardarAlimento = new JButton("Guardar Alimento");
        btnGuardarAlimento.setBackground(new Color(96, 207, 174));
        btnGuardarAlimento.setForeground(Color.BLACK);
        btnGuardarAlimento.setFocusPainted(false);
        btnGuardarAlimento.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelAlimento.add(btnGuardarAlimento);

        panelFormularios.add(panelDonador);
        panelFormularios.add(Box.createVerticalStrut(10));
        panelFormularios.add(panelAlimento);

        areaDatos = new JTextArea();
        areaDatos.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
        areaDatos.setEditable(false);
        areaDatos.setBackground(new Color(240, 243, 244));

        JScrollPane scrollPane = new JScrollPane(areaDatos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Datos Registrados"));
        scrollPane.getViewport().setBackground(new Color(111, 204, 237));

        panelPrincipal.add(panelFormularios, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal);

        btnGuardarDonador.addActionListener(e -> {
            String nombre = txtNombreDonador.getText();
            String contacto = txtContactoDonador.getText();

            if (!nombre.isEmpty() && !contacto.isEmpty()) {
                Donador nuevo = new Donador(nombre, contacto);
                donadores.add(nuevo);
                comboDonadores.addItem(nuevo);
                mostrarDatos();
                txtNombreDonador.setText("");
                txtContactoDonador.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Completa todos los campos del donador.");
            }
        });
        //-------------

        btnGuardarAlimento.addActionListener(e -> {
            Donador donadorSeleccionado = (Donador) comboDonadores.getSelectedItem();
            String nombre = txtNombreAlimento.getText();
            String cantidad = txtCantidadAlimento.getText();
            int anio = (int) comboAnio.getSelectedItem();
            int mes = (int) comboMes.getSelectedItem();
            int dia = (int) comboDia.getSelectedItem();
            LocalDate fechaExp = LocalDate.of(anio, mes, dia);

            if (donadorSeleccionado != null && !nombre.isEmpty() && !cantidad.isEmpty()) {
                String descripcion = nombre + " - " + cantidad + " unidades";

                String[] opciones = {
                        "Fundación Infantil", "Comedor Comunitario", "Ancianato", "Cualquiera disponible"
                };
                String fundacionSeleccionada = (String) JOptionPane.showInputDialog(
                        null,
                        "¿A qué fundación deseas donar este alimento?",
                        "Seleccionar Fundación",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (fundacionSeleccionada != null) {
                    Alimento nuevoAlimento = new Alimento(descripcion, fechaExp, fundacionSeleccionada);
                    donadorSeleccionado.alimentos.add(nuevoAlimento);

                    JOptionPane.showMessageDialog(
                            null,
                            "🥫 El alimento fue donado a la fundación: " + fundacionSeleccionada
                    );

                    mostrarDatos();
                }
                txtNombreAlimento.setText("");
                txtCantidadAlimento.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Completa todos los campos del alimento y selecciona un donador.");
            }
        });

        setVisible(true);
    }

    private void mostrarDatos() {
        areaDatos.setText("");
        ArrayList<String> caducados = new ArrayList<>();
        ArrayList<String> proximos = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        areaDatos.append("📋 REGISTRO DE DONACIONES\n");
        areaDatos.append("----------------------------------------------------------------\n");

        for (Donador d : donadores) {
            areaDatos.append("\n👤 Donador: " + d + "\n");
            if (d.getAlimentos().isEmpty()) {
                areaDatos.append("   🔸 (No ha donado alimentos aún)\n");
            } else {
                for (Alimento a : d.getAlimentos()) {
                    long diasRestantes = ChronoUnit.DAYS.between(hoy, a.getFechaExpiracion());
                    String fecha = a.getFechaExpiracion().format(formatoFecha);
                    areaDatos.append("   🥫 " + a.getDescripcion() + "\n");
                    areaDatos.append("      📅 Vence: " + fecha + "\n");
                    areaDatos.append("      🏥 Fundación: " + a.getFundacion() + "\n");

                    if (diasRestantes < 0) {
                        caducados.add("❌ " + d + ": " + a.getDescripcion() + " (expiró hace " + Math.abs(diasRestantes) + " días)");
                    } else {
                        proximos.add("✅ " + d + ": " + a.getDescripcion() + " (faltan " + diasRestantes + " días)");
                    }
                }
            }
        }

        areaDatos.append("\n-----------------------------------------------------------------\n");
        areaDatos.append("⏳ ESTADO DE VENCIMIENTO DE ALIMENTOS\n");

        areaDatos.append("\n⚠ Alimentos CADUCADOS:\n");
        if (caducados.isEmpty()) {
            areaDatos.append("   ✅ Ninguno\n");
        } else {
            for (String c : caducados) areaDatos.append("   " + c + "\n");
        }

        areaDatos.append("\n📆 Alimentos AÚN VÁLIDOS:\n");
        if (proximos.isEmpty()) {
            areaDatos.append("   ❌ Ninguno\n");
        } else {
            for (String p : proximos) areaDatos.append("   " + p + "\n");
        }

        areaDatos.append("\n----------------------------------------------------------------\n");
        areaDatos.append("🏥 ALIMENTOS POR FUNDACIÓN\n");

        Map<String, ArrayList<String>> alimentosPorFundacion = new HashMap<>();

        for (Donador d : donadores) {
            for (Alimento a : d.getAlimentos()) {
                alimentosPorFundacion
                        .computeIfAbsent(a.getFundacion(), k -> new ArrayList<>())
                        .add("👤 " + d + " → 🥫 " + a.getDescripcion() + " (vence: " + a.getFechaExpiracion().format(formatoFecha) + ")");
            }
        }

        for (String fundacion : alimentosPorFundacion.keySet()) {
            areaDatos.append("\n🏥 Fundación: " + fundacion + "\n");
            for (String detalle : alimentosPorFundacion.get(fundacion)) {
                areaDatos.append("   " + detalle + "\n");
            }
        }

        areaDatos.append("\n----------------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        new SistemaGestionAlimentos();
    }
}
