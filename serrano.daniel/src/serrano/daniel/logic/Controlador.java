package serrano.daniel.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controlador {

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


    public void registrarUsuario(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                                 String contrasena, String correoElectronico, int puntuacion, String direccion) {


        Usuario nuevoUsuario = new Usuario(nombreCompleto, identificacion, fechaNacimiento,
                contrasena, correoElectronico, puntuacion, direccion);

        listaUsuarios.add(nuevoUsuario);
    }


    public ArrayList<Usuario> listarUsuarios() {
        return listaUsuarios;
    }

    public Usuario buscarUsuarioPorId(String identificacion) {
        for (Usuario u : listaUsuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return u; // Si lo encuentra, lo devuelve
            }
        }
        return null; // Si no lo encuentra, devuelve nulo
    }


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

    public void registrarOferta(Usuario oferente, double precioOfertado) {
        Oferta nuevaOferta = new Oferta(oferente, precioOfertado);
        listaOfertas.add(nuevaOferta);
    }

    public ArrayList<Oferta> listarOfertas() {
        return listaOfertas;
    }
}