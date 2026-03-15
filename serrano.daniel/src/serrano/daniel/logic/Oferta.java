package serrano.daniel.logic;

public class Oferta {


    private Usuario oferente;

    private double precioOfertado;

    public Oferta() {
    }

    public Oferta(Usuario oferente, double precioOfertado) {
        this.oferente = oferente;
        this.precioOfertado = precioOfertado;
    }

    public Usuario getOferente() { return oferente; }
    public void setOferente(Usuario oferente) { this.oferente = oferente; }

    public double getPrecioOfertado() { return precioOfertado; }
    public void setPrecioOfertado(double precioOfertado) { this.precioOfertado = precioOfertado; }

    @Override
    public String toString() {
        return "Oferta de: " + oferente.getNombreCompleto() +
                " | Puntuación del oferente: " + oferente.getPuntuacion() +
                " | Monto: $" + precioOfertado;
    }
}