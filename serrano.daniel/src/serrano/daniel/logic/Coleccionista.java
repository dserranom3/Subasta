package serrano.daniel.logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Coleccionista extends Usuario {

    // Atributos compartidos con el Vendedor
    private int puntuacion;
    private String direccion;

    // Colecciones exclusivas del Coleccionista
    // Usamos String para los intereses (ej. "Monedas", "Arte") y nuestra clase ObjetoOfrecido para sus pertenencias.
    private ArrayList<String> intereses;
    private ArrayList<ObjetoOfrecido> objetosPropios;

    // 1. CONSTRUCTOR POR DEFECTO
    public Coleccionista() {
        super(); // Llama al constructor vacío de Usuario
        // ¡Regla de oro! Siempre inicializar las listas para evitar el error NullPointerException
        this.intereses = new ArrayList<>();
        this.objetosPropios = new ArrayList<>();
    }

    // 2. CONSTRUCTOR CON PARÁMETROS
    public Coleccionista(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                         String contrasena, String correoElectronico, int puntuacion, String direccion) {

        // Enviamos los datos base a la clase Padre
        super(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico);

        // Guardamos los datos propios
        this.puntuacion = puntuacion;
        this.direccion = direccion;

        // Inicializamos las listas vacías listas para ser usadas
        this.intereses = new ArrayList<>();
        this.objetosPropios = new ArrayList<>();
    }

    // --- GETTERS Y SETTERS ---

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public ArrayList<String> getIntereses() { return intereses; }
    public void setIntereses(ArrayList<String> intereses) { this.intereses = intereses; }

    public ArrayList<ObjetoOfrecido> getObjetosPropios() { return objetosPropios; }
    public void setObjetosPropios(ArrayList<ObjetoOfrecido> objetosPropios) { this.objetosPropios = objetosPropios; }

    // 3. SOBRESCRIBIR toString
    @Override
    public String toString() {
        return "[COLECCIONISTA] " + super.toString() +
                " | Puntos: " + puntuacion +
                " | Objetos registrados: " + objetosPropios.size();
    }
}