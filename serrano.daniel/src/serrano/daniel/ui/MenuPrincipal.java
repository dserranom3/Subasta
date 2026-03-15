package serrano.daniel.ui;

import serrano.daniel.logic.Controlador;
import serrano.daniel.logic.Usuario;
import serrano.daniel.logic.Subasta;
import serrano.daniel.logic.Oferta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

    private Controlador admin;
    private Scanner scanner;

    public MenuPrincipal() {
        this.admin = new Controlador();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n=== PLATAFORMA DE SUBASTAS ===");
            System.out.println("1. Registrar un nuevo usuario");
            System.out.println("2. Listar todos los usuarios");
            System.out.println("3. Registrar una subasta");
            System.out.println("4. Listar todas las subastas");
            System.out.println("5. Registrar una oferta");
            System.out.println("6. Listar todas las ofertas");
            System.out.println("0. Salir del sistema");
            System.out.print("Digita una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: registrarUsuarioUI(); break;
                    case 2: listarUsuariosUI(); break;
                    case 3: registrarSubastaUI(); break;
                    case 4: listarSubastasUI(); break;
                    case 5: registrarOfertaUI(); break;
                    case 6: listarOfertasUI(); break;
                    case 0: System.out.println("¡Gracias por usar el sistema!"); break;
                    default: System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa solo números enteros.");
            }

        } while (opcion != 0);
    }

    // ==========================================
    // MÉTODOS DE USUARIOS
    // ==========================================
    private void registrarUsuarioUI() {
        System.out.println("\n--- REGISTRO DE USUARIO ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
        String fechaTexto = scanner.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fechaTexto, formato);

        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Puntuación inicial (ej. 0): ");
        int puntos = Integer.parseInt(scanner.nextLine());
        System.out.print("Dirección física: ");
        String direccion = scanner.nextLine();

        admin.registrarUsuario(nombre, id, fechaNac, pass, correo, puntos, direccion);
        System.out.println("¡Usuario registrado con éxito!");
    }

    private void listarUsuariosUI() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        ArrayList<Usuario> lista = admin.listarUsuarios();
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : lista) {
                System.out.println(u);
            }
        }
    }

    // ==========================================
    // MÉTODOS DE SUBASTAS
    // ==========================================
    private void registrarSubastaUI() {
        System.out.println("\n--- REGISTRO DE SUBASTA ---");
        System.out.print("Ingresa la identificación del usuario creador: ");
        String idUsuario = scanner.nextLine();

        // 1. Buscamos si el usuario existe en nuestra memoria
        Usuario creador = admin.buscarUsuarioPorId(idUsuario);

        if (creador == null) {
            System.out.println("Error: No se encontró un usuario con esa identificación. Regístralo primero.");
            return; // Cortamos la ejecución del método aquí
        }

        // 2. Si el usuario existe, pedimos los datos de la subasta
        System.out.print("Fecha de vencimiento (DD/MM/AAAA HH:MM) [Ej. 25/12/2026 15:30]: ");
        String fechaTexto = scanner.nextLine();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaVencimiento = LocalDateTime.parse(fechaTexto, formatoHora);

        System.out.print("Precio mínimo de aceptación ($): ");
        double precioMinimo = Double.parseDouble(scanner.nextLine());

        System.out.print("Estado inicial (Ej. Activa, Pendiente): ");
        String estado = scanner.nextLine();

        // Le pasamos el objeto 'creador' completo al controlador, cumpliendo con la relación UML
        admin.registrarSubasta(fechaVencimiento, creador, creador.getPuntuacion(), precioMinimo, estado);
        System.out.println("¡Subasta registrada con éxito por " + creador.getNombreCompleto() + "!");
    }

    private void listarSubastasUI() {
        System.out.println("\n--- LISTA DE SUBASTAS ---");
        ArrayList<Subasta> lista = admin.listarSubastas();
        if (lista.isEmpty()) {
            System.out.println("No hay subastas registradas.");
        } else {
            for (Subasta s : lista) {
                System.out.println(s);
            }
        }
    }

    // ==========================================
    // MÉTODOS DE OFERTAS
    // ==========================================
    private void registrarOfertaUI() {
        System.out.println("\n--- REGISTRO DE OFERTA ---");
        System.out.print("Ingresa la identificación del usuario oferente: ");
        String idUsuario = scanner.nextLine();

        Usuario oferente = admin.buscarUsuarioPorId(idUsuario);

        if (oferente == null) {
            System.out.println("Error: El usuario no existe. No puede realizar ofertas.");
            return;
        }

        System.out.print("Monto a ofertar ($): ");
        double monto = Double.parseDouble(scanner.nextLine());

        admin.registrarOferta(oferente, monto);
        System.out.println("¡Oferta de $" + monto + " registrada con éxito!");
    }

    private void listarOfertasUI() {
        System.out.println("\n--- LISTA DE OFERTAS ---");
        ArrayList<Oferta> lista = admin.listarOfertas();
        if (lista.isEmpty()) {
            System.out.println("No hay ofertas registradas.");
        } else {
            for (Oferta o : lista) {
                System.out.println(o);
            }
        }
    }
}