package serrano.daniel.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controlador {

    // 1. LAS LISTAS EN MEMORIA
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Subasta> listaSubastas;
    private ArrayList<ObjetoOfrecido> listaObjetos;
    private ArrayList<Oferta> listaOfertas;

    public Controlador() {
        this.listaUsuarios = new ArrayList<>();
        this.listaSubastas = new ArrayList<>();
        this.listaObjetos = new ArrayList<>();
        this.listaOfertas = new ArrayList<>();
    }

    // =========================================================================
    // VALIDACIONES Y REGLAS DE NEGOCIO
    // =========================================================================

    public boolean existeModerador() {
        for (Usuario u : listaUsuarios) {
            // "instanceof" nos permite preguntar si un objeto pertenece a una clase específica
            if (u instanceof Moderador) {
                return true;
            }
        }
        return false;
    }

    // =========================================================================
    // REGISTRO DE USUARIOS (HERENCIA APLICADA)
    // =========================================================================

    public boolean registrarModerador(String nombre, String id, LocalDate fechaNac, String pass, String correo) {
        Moderador nuevoMod = new Moderador(nombre, id, fechaNac, pass, correo);

        // Regla 8: El moderador debe ser mayor de edad
        if (nuevoMod.getEdad() < 18) {
            return false;
        }
        // Regla 2: Solo puede haber un único moderador
        if (existeModerador()) {
            return false;
        }

        listaUsuarios.add(nuevoMod);
        return true; // Registro exitoso
    }

    public boolean registrarVendedor(String nombre, String id, LocalDate fechaNac, String pass, String correo, int puntos, String direccion) {
        Vendedor nuevoVend = new Vendedor(nombre, id, fechaNac, pass, correo, puntos, direccion);

        // Regla 7: El vendedor debe ser mayor de edad
        if (nuevoVend.getEdad() < 18) {
            return false;
        }

        listaUsuarios.add(nuevoVend);
        return true;
    }

    public boolean registrarColeccionista(String nombre, String id, LocalDate fechaNac, String pass, String correo, int puntos, String direccion) {
        Coleccionista nuevoCol = new Coleccionista(nombre, id, fechaNac, pass, correo, puntos, direccion);

        // Regla 7: El coleccionista debe ser mayor de edad
        if (nuevoCol.getEdad() < 18) {
            return false;
        }

        listaUsuarios.add(nuevoCol);
        return true;
    }

    // =========================================================================
    // MÉTODOS DE BÚSQUEDA Y LISTADO
    // =========================================================================

    public ArrayList<Usuario> listarUsuarios() {
        return listaUsuarios;
    }

    public Usuario buscarUsuarioPorId(String identificacion) {
        for (Usuario u : listaUsuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return u;
            }
        }
        return null;
    }

    // =========================================================================
    // MÉTODOS PARA SUBASTAS Y OFERTAS (Se mantienen igual por ahora)
    // =========================================================================

    public void registrarSubasta(LocalDateTime fechaVencimiento, Usuario creador,
                                 int puntuacionCreador, double precioMinimo, String estado) {
        Subasta nuevaSubasta = new Subasta(fechaVencimiento, creador, puntuacionCreador, precioMinimo, estado);
        listaSubastas.add(nuevaSubasta);
    }

    public ArrayList<Subasta> listarSubastas() {
        return listaSubastas;
    }

    public void registrarObjeto(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        ObjetoOfrecido nuevoObjeto = new ObjetoOfrecido(nombre, descripcion, estado, fechaCompra);
        listaObjetos.add(nuevoObjeto);
    }

    public void registrarOferta(Coleccionista oferente, double precioOfertado) {
        Oferta nuevaOferta = new Oferta(oferente, precioOfertado);
        listaOfertas.add(nuevaOferta);
    }

    public ArrayList<Oferta> listarOfertas() {
        return listaOfertas;
    }
}