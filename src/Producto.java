public class Producto implements Comparable<Producto>{
    private int idProducto;
    private String nombreProducto;
    private double precioActual;
    private int mes;
    private String tipo;



    public Producto() {}

    public Producto(int idProducto, String nombreProducto ) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public int getMes() {
        return mes;
    }



    public void setMes(int mes) {
        this.mes = mes;
    }

    public int compareTo(Producto otro){

        if (this.idProducto < otro.idProducto) {
            return -1;
        }
        else if (this.idProducto > otro.idProducto) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
