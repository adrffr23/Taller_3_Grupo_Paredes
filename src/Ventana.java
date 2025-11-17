import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JSpinner spnLap;
    private JSpinner spnPc;
    private JSpinner spnCel;
    private JButton btnAgregar;
    private JLabel jlbPrecioCel;
    private JLabel jlbPrecioPc;
    private JLabel jlbPrecioLap;
    private JTextField txtBusId;
    private JButton btnID;
    private JTextArea textArea1;
    private JComboBox cmbProduct;
    private JButton btnBusPro;
    private JList list1;
    private JComboBox cbxActualizar;
    private JTextField txtActulizar;
    private JButton btnActualizar;
    double precioactLAp=1395.90;
    double precioactPC=1110.50;
    double precioactCel= 863.84;

    Tienda tienda = new Tienda();

    public Ventana() {

        spnLap.setModel(new SpinnerNumberModel(0,0 , 10, 1));
        spnPc.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        spnCel.setModel(new SpinnerNumberModel(0,0 , 10, 1));

        cmbProduct.addItem("Laptop");
        cmbProduct.addItem("PC");
        cmbProduct.addItem("Celular");

        jlbPrecioLap.setText("1395.90");
        jlbPrecioPc.setText("1110.50");
        jlbPrecioCel.setText("863.84");

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cantLap = ((Number) spnLap.getValue()).doubleValue();
                double cantPc  = ((Number) spnPc.getValue()).doubleValue();
                double cantCel = ((Number) spnCel.getValue()).doubleValue();

                tienda.actualizarMeses();

                for (int i = 0; i < cantLap; i++) {
                    Producto p = new Producto(tienda.generarID(), "Laptop");
                    p.setPrecioActual(precioactLAp);
                    tienda.anadirPro(p, 1);
                    p.setPrecioActual(p.getPrecioActual() + 0.15);
                    jlbPrecioLap.setText(String.format("%.2f", p.getPrecioActual()));
                }



                for (int i = 0; i < cantPc; i++) {
                    Producto p = new Producto(tienda.generarID(), "PC");
                    p.setPrecioActual(precioactPC);
                    tienda.anadirPro(p, 1);


                    if (i == cantPc - 1) {
                        tienda.inflacion(p);
                        jlbPrecioPc.setText(String.format("%.2f", p.getPrecioActual()));
                    }
                }


                for (int i = 0; i < cantCel; i++) {
                    Producto p = new Producto(tienda.generarID(), "Celular");
                    p.setPrecioActual(precioactCel);
                    tienda.anadirPro(p, 1);


                    if (i == cantCel - 1) {
                        tienda.inflacion(p);
                        jlbPrecioCel.setText(String.format("%.2f", p.getPrecioActual()));
                    }
                }
                JOptionPane.showMessageDialog(null , "Los productos se han aggregado correctamente");
            }
        });
        btnID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtBusId.getText());
                    String resultado = tienda.busquedaBinaria(id);
                    textArea1.setText(resultado);
                } catch (NumberFormatException ex) {
                    textArea1.setText("Ingrese un ID válido.");
                }
            }
        });




        btnBusPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) cmbProduct.getSelectedItem();

                Producto temp = new Producto();
                temp.setNombreProducto(nombre);

                List<Producto> resultados = tienda.buscarNombre(temp);

                DefaultListModel<String> modelo = new DefaultListModel<>();

                if (resultados.isEmpty()) {
                    modelo.addElement("No se encontraron productos.");
                } else {
                    for (Producto p : resultados) {
                        modelo.addElement(
                                "ID: " + p.getIdProducto() +
                                        " | Producto: " + p.getNombreProducto() +
                                        " | Mes: " + p.getMes()
                        );
                    }
                }

                list1.setModel(modelo);
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = cbxActualizar.getSelectedItem().toString();
                String nuevo = txtActulizar.getText(); // SIN parsear, SIN formato

                if (tipo.equals("PC")) {
                    jlbPrecioPc.setText(nuevo);
                    precioactPC = Double.parseDouble(nuevo);
                    for(Producto a: tienda.getProductos()){
                        if(a.getNombreProducto().equals("PC")){
                            a.setPrecioActual(Integer.parseInt(nuevo));
                        }

                    }
                }
                else if (tipo.equals("Laptop")) {
                    jlbPrecioLap.setText(nuevo);
                    precioactLAp = Double.parseDouble(nuevo);
                    for(Producto a: tienda.getProductos()){
                        if(a.getNombreProducto().equals("Laptop")){
                            a.setPrecioActual(Integer.parseInt(nuevo));
                        }
                    }
                }
                else if (tipo.equals("Celular")) {
                    jlbPrecioCel.setText(nuevo);
                    precioactCel = Double.parseDouble(nuevo);
                    for(Producto a: tienda.getProductos()){
                        if(a.getNombreProducto().equals("Celular")){
                            a.setPrecioActual(Integer.parseInt(nuevo));
                        }
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


