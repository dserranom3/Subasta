package serrano.daniel.logic;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class Subasta {

    private LocalDateTime fechaVencimiento;

    private Usuario creador;

    private int puntuacionCreador;
    private double precioMinimoAceptacion;
    private String estado;


    private ArrayList<ObjetoOfrecido> objetosSubastados;

    public Subasta() {
        this.objetosSubastados = new ArrayList<>();
    }

    public Subasta(LocalDateTime fechaVencimiento, Usuario creador, int puntuacionCreador, double precioMinimoAceptacion, String estado) {
        this.fechaVencimiento = fechaVencimiento;
        this.creador = creador;
        this.puntuacionCreador = puntuacionCreador;
        this.precioMinimoAceptacion = precioMinimoAceptacion;
        this.estado = estado;
        this.objetosSubastados = new ArrayList<>();
    }


    public String getTiempoRestante() {
        if (fechaVencimiento != null && LocalDateTime.now().isBefore(fechaVencimiento)) {
            Duration duracion = Duration.between(LocalDateTime.now(), fechaVencimiento);
            long dias = duracion.toDays();
            long horas = duracion.toHoursPart();
            long minutos = duracion.toMinutesPart();
            long segundos = duracion.toSecondsPart();

            return dias + " días, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos.";
        }
        return "La subasta ha finalizado.";
    }

    public LocalDateTime getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public int getPuntuacionCreador() { return puntuacionCreador; }
    public void setPuntuacionCreador(int puntuacionCreador) { this.puntuacionCreador = puntuacionCreador; }

    public double getPrecioMinimoAceptacion() { return precioMinimoAceptacion; }
    public void setPrecioMinimoAceptacion(double precioMinimoAceptacion) { this.precioMinimoAceptacion = precioMinimoAceptacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public ArrayList<ObjetoOfrecido> getObjetosSubastados() { return objetosSubastados; }
    public void setObjetosSubastados(ArrayList<ObjetoOfrecido> objetosSubastados) { this.objetosSubastados = objetosSubastados; }

    @Override
    public String toString() {
        return "Subasta [" + estado + "] - Creada por: " + creador.getNombreCompleto() +
                " | Precio mínimo: $" + precioMinimoAceptacion +
                " | Tiempo restante: " + getTiempoRestante();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subasta subasta = (Subasta) o;
        // Son iguales si tienen la misma fecha límite Y el mismo creador
        return java.util.Objects.equals(fechaVencimiento, subasta.fechaVencimiento) &&
                java.util.Objects.equals(creador, subasta.creador);
    }
}