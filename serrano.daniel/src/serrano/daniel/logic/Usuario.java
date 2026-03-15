package serrano.daniel.logic;

// Importamos las librerías necesarias para manejar las fechas
import java.time.LocalDate;
import java.time.Period;

public class Usuario {

    private String nombreCompleto;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String contrasena;
    private String correoElectronico;


    private int puntuacion;
    private String direccion;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                   String contrasena, String correoElectronico, int puntuacion, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.puntuacion = puntuacion;
        this.direccion = direccion;
    }


    public int getEdad() {
        if (this.fechaNacimiento != null) {
            return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
        }
        return 0;
    }


    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }


    @Override
    public String toString() {
        return "Usuario [" + identificacion + "] - " + nombreCompleto +
                " | Edad: " + getEdad() + " años | Correo: " + correoElectronico +
                " | Puntuación: " + puntuacion + " | Dirección: " + direccion;
    }
}