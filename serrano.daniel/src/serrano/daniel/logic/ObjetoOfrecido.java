package serrano.daniel.logic;

import java.time.LocalDate;
import java.time.Period;

public class ObjetoOfrecido {

    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;

    public ObjetoOfrecido() {
    }

    public ObjetoOfrecido(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    public String getAntiguedad() {
        if (this.fechaCompra != null) {
            // Comparamos la fecha de compra con la fecha actual
            Period periodo = Period.between(this.fechaCompra, LocalDate.now());
            int anos = periodo.getYears();
            int meses = periodo.getMonths();
            int dias = periodo.getDays();

            return anos + " años, " + meses + " meses y " + dias + " días";
        }
        return "Fecha de compra no registrada";
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }


    @Override
    public String toString() {
        return "Objeto: " + nombre + " (" + estado + ") | Antigüedad: " + getAntiguedad();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Es exactamente el mismo en memoria
        if (o == null || getClass() != o.getClass()) return false; // Es de otra familia
        ObjetoOfrecido that = (ObjetoOfrecido) o;
        // Comparamos solo por el nombre del objeto
        return java.util.Objects.equals(nombre, that.nombre);
    }
}