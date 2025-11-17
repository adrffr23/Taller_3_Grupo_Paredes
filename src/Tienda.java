import java.util.*;

public class Tienda {

    private HashSet<Integer> idsUsados = new HashSet<>();
    private Random random = new Random();

    private List<Producto> productos = new ArrayList<>();


    public void actualizarMeses() {


        for (Producto p : productos) {
            p.setMes(p.getMes() + 1);
        }

        productos.removeIf(p -> p.getMes() >= 4);
    }


    public void inflacion(Producto producto){
        producto.setPrecioActual(producto.getPrecioActual() + 0.15);

    }

    public int generarID() {
        int id;

        do {
            id = random.nextInt(9000) + 1000; // genera entre 1000 y 9999
        } while (idsUsados.contains(id));

        idsUsados.add(id);
        return id;
    }

    public void anadirPro(Producto p, int mes) {
        p.setMes(mes);
        productos.add(p);
    }


    public List<Producto> buscarNombre(Producto temp) {
        List<Producto> encontrados = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getNombreProducto().equalsIgnoreCase(temp.getNombreProducto())) {
                encontrados.add(p);
            }
        }

        return encontrados;
    }


    public String busquedaBinaria(int id) {
        if (productos.isEmpty()) {
            return "No hay productos registrados.";
        }

        Collections.sort(productos);

        int inicio = 0;
        int fin = productos.size() - 1;

        while (inicio <= fin) {
            int mid = (inicio + fin) / 2;

            if (productos.get(mid).getIdProducto() == id) {
                Producto p = productos.get(mid);
                return "Encontrado:\nID: " + p.getIdProducto() +
                        " | Nombre: " + p.getNombreProducto() +
                        " | Precio: $" + p.getPrecioActual() +
                        " | Mes: " + p.getMes();
            }

            if (productos.get(mid).getIdProducto() < id) {
                inicio = mid + 1;
            } else {
                fin = mid - 1;
            }
        }

        return "Producto con ID " + id + " no encontrado.";
    }
    public  List<Producto> getProductos(){
        return productos;
    }

}