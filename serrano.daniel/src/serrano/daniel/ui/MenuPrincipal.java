package serrano.daniel.ui;

import serrano.daniel.logic.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

    private Controlador admin;
    private Scanner scanner;
    // ¡NUEVO! Variable para saber quién inició sesión
    private Usuario usuarioLogueado;

    public MenuPrincipal() {
        this.admin = new Controlador();
        this.scanner = new Scanner(System.in);
        this.usuarioLogueado = null; // Al iniciar, nadie ha iniciado sesión
    }

    public void mostrarMenu() {
        // REGLA 1: Validar si existe un moderador al arrancar
        validarModeradorInicial();

        int opcion = -1;
        do {
            // Control de flujo: Si no hay nadie logueado, mostramos el menú de acceso
            if (usuarioLogueado == null) {
                opcion = mostrarMenuAcceso();
            } else {
                // Si alguien ya inició sesión, mostramos el menú principal
                opcion = mostrarMenuOperaciones();
            }
        } while (opcion != 0);
    }

    // ==========================================
    // FASE 1: VALIDACIÓN INICIAL Y ACCESO
    // ==========================================

    private void validarModeradorInicial() {
        System.out.println("\n=== VERIFICACIÓN DEL SISTEMA ===");
        if (!admin.existeModerador()) {
            System.out.println("ADVERTENCIA: No existe un Moderador en el sistema.");
            System.out.println("Por reglas de negocio, debe registrar el primer Moderador para continuar.");

            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Identificación: ");
            String id = scanner.nextLine();
            System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
            LocalDate fechaNac = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();
            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();

            boolean exito = admin.registrarModerador(nombre, id, fechaNac, pass, correo);
            if (exito) {
                System.out.println("¡Moderador registrado con éxito! El sistema se ha desbloqueado.");
            } else {
                System.out.println("ERROR: El moderador debe ser mayor de edad. Reinicie el programa.");
                System.exit(0); // Apaga el programa si no cumple la regla de edad
            }
        }
    }

    private int mostrarMenuAcceso() {
        System.out.println("\n=== BIENVENIDO A LA PLATAFORMA DE SUBASTAS ===");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar un nuevo usuario (Vendedor/Coleccionista)");
        System.out.println("0. Salir del sistema");
        System.out.print("Digita una opción: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: iniciarSesionUI(); break;
                case 2: registrarUsuarioUI(); break;
                case 0: System.out.println("¡Hasta pronto!"); break;
                default: System.out.println("Opción no válida.");
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingresa un número.");
            return -1;
        }
    }

    private void iniciarSesionUI() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        Usuario encontrado = admin.buscarUsuarioPorId(id);

        // Validamos que exista y que la contraseña coincida
        if (encontrado != null && encontrado.getContrasena().equals(pass)) {
            usuarioLogueado = encontrado; // ¡Se guarda el usuario activo!
            System.out.println("¡Bienvenido(a), " + usuarioLogueado.getNombreCompleto() + "!");
        } else {
            System.out.println("Error: Credenciales incorrectas o usuario no existe.");
        }
    }

    // ==========================================
    // FASE 2: OPERACIONES DEL SISTEMA
    // ==========================================

    private int mostrarMenuOperaciones() {
        System.out.println("\n=== MENÚ PRINCIPAL (" + usuarioLogueado.getNombreCompleto() + ") ===");
        System.out.println("1. Listar todos los usuarios");
        System.out.println("2. Crear una subasta");
        System.out.println("3. Listar todas las subastas");
        System.out.println("4. Crear una oferta");
        System.out.println("5. Listar todas las ofertas");
        System.out.println("9. Cerrar Sesión");
        System.out.println("0. Salir del sistema");
        System.out.print("Digita una opción: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: listarUsuariosUI(); break;
                case 2: registrarSubastaUI(); break;
                case 3: listarSubastasUI(); break;
                case 4: registrarOfertaUI(); break;
                case 5: listarOfertasUI(); break;
                case 9:
                    usuarioLogueado = null; // Borramos la sesión
                    System.out.println("Sesión cerrada exitosamente.");
                    opcion = -1; // Para que el bucle no se rompa y vuelva al login
                    break;
                case 0: System.out.println("¡Gracias por usar el sistema!"); break;
                default: System.out.println("Opción no válida.");
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingresa un número.");
            return -1;
        }
    }

    // --- MÉTODOS DE REGISTRO (Adaptados a la Herencia) ---

    private void registrarUsuarioUI() {
        System.out.println("\n--- REGISTRO DE USUARIO ---");
        System.out.println("¿Qué tipo de usuario deseas registrar?");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");
        System.out.print("Opción: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
        LocalDate fechaNac = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Puntuación inicial: ");
        int puntos = Integer.parseInt(scanner.nextLine());
        System.out.print("Dirección física: ");
        String direccion = scanner.nextLine();

        boolean exito = false;
        if (tipo == 1) {
            exito = admin.registrarVendedor(nombre, id, fechaNac, pass, correo, puntos, direccion);
        } else if (tipo == 2) {
            exito = admin.registrarColeccionista(nombre, id, fechaNac, pass, correo, puntos, direccion);
        }

        if (exito) {
            System.out.println("¡Usuario registrado con éxito!");
        } else {
            System.out.println("Error: El usuario debe ser mayor de edad para registrarse.");
        }
    }

    private void listarUsuariosUI() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (Usuario u : admin.listarUsuarios()) {
            System.out.println(u); // Gracias a la herencia, esto imprimirá si es [MODERADOR], [VENDEDOR], etc.
        }
    }

    private void registrarSubastaUI() {
        // Regla: El moderador no puede participar en subastas
        if (usuarioLogueado instanceof Moderador) {
            System.out.println("Error: El Moderador no tiene permitido crear subastas.");
            return;
        }

        System.out.println("\n--- REGISTRO DE SUBASTA ---");
        System.out.print("Fecha de vencimiento (DD/MM/AAAA HH:MM): ");
        LocalDateTime fechaVencimiento = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.print("Precio mínimo de aceptación ($): ");
        double precioMinimo = Double.parseDouble(scanner.nextLine());

        // Extraemos la puntuación dependiendo de si es Vendedor o Coleccionista
        int puntos = 0;
        if (usuarioLogueado instanceof Vendedor) puntos = ((Vendedor) usuarioLogueado).getPuntuacion();
        if (usuarioLogueado instanceof Coleccionista) puntos = ((Coleccionista) usuarioLogueado).getPuntuacion();

        admin.registrarSubasta(fechaVencimiento, usuarioLogueado, puntos, precioMinimo, "Activa");
        System.out.println("¡Subasta creada por " + usuarioLogueado.getNombreCompleto() + "!");
    }

    private void listarSubastasUI() {
        System.out.println("\n--- LISTA DE SUBASTAS ---");
        for (Subasta s : admin.listarSubastas()) {
            System.out.println(s);
        }
    }

    private void registrarOfertaUI() {
        // Regla: Solo los coleccionistas pueden hacer ofertas. Moderadores y Vendedores no pueden[cite: 231, 232].
        if (!(usuarioLogueado instanceof Coleccionista)) {
            System.out.println("Error: Solo los Coleccionistas tienen permisos para realizar ofertas.");
            return;
        }

        System.out.println("\n--- REGISTRO DE OFERTA ---");
        System.out.print("Monto a ofertar ($): ");
        double monto = Double.parseDouble(scanner.nextLine());

        // Hacemos un "casting" seguro porque ya validamos arriba que sí es un coleccionista
        admin.registrarOferta((Coleccionista) usuarioLogueado, monto);
        System.out.println("¡Oferta de $" + monto + " registrada con éxito!");
    }

    private void listarOfertasUI() {
        System.out.println("\n--- LISTA DE OFERTAS ---");
        for (Oferta o : admin.listarOfertas()) {
            System.out.println(o);
        }
    }
}