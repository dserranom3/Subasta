package serrano.daniel.logic;

public class Oferta {

    private Coleccionista oferente;

    private double precioOfertado;


    public Oferta() {
    }


    public Oferta(Coleccionista oferente, double precioOfertado) {
        this.oferente = oferente;
        this.precioOfertado = precioOfertado;
    }


    public Coleccionista getOferente() { return oferente; }
    public void setOferente(Coleccionista oferente) { this.oferente = oferente; }

    public double getPrecioOfertado() { return precioOfertado; }
    public void setPrecioOfertado(double precioOfertado) { this.precioOfertado = precioOfertado; }


    @Override
    public String toString() {
        // Como ahora es un Coleccionista, ¡Java ya reconoce el getPuntuacion() sin problemas!
        return "Oferta de: " + oferente.getNombreCompleto() +
                " | Puntuación del oferente: " + oferente.getPuntuacion() +
                " | Monto: $" + precioOfertado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oferta oferta = (Oferta) o;
        return Double.compare(oferta.precioOfertado, precioOfertado) == 0 &&
                java.util.Objects.equals(oferente, oferta.oferente);
    }
}