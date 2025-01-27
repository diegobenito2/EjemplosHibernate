import com.example.HibernateUtil;
import com.example.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        formatter.setLenient(false); // Esto ayuda a que no acepte fechas inválidas (como 2025-02-30)
        int opcion = -1;
        do {
            System.out.print("¿Qué desea hacer?: " +
                    "\n\tListar     -> 1" +
                    "\n\tInsertar   -> 2" +
                    "\n\tActualizar -> 3" +
                    "\n\tEliminar   -> 4" +
                    "\n\tSalir      -> 0" +
                    "\nElige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    listar();
                }
                case 2 -> {
                    insertar();
                }
                case 3 -> {
                    actualizar();
                }
                case 4 -> {
                    eliminar();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    break;
                }
            }
        } while (opcion != 0);
    }

    public static int opciones() {
        int opcion = -1;
        do {
            System.out.print("¿En qué tabla?: " +
                    "\n\tUsuarios   -> 1" +
                    "\n\tPerfiles   -> 2" +
                    "\n\tSeries     -> 3" +
                    "\n\tEpisodios  -> 4" +
                    "\n\tHistorial  -> 5" +
                    "\n\tSalir      -> 0" +
                    "\nElige una opción: ");
            return opcion = sc.nextInt();
        } while (opcion != 0);
    }

    public static void listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int opcion = -1;
        do {
            opcion = opciones();
            switch (opcion) {
                case 1 -> {
                    listarUsuarios();
                }
                case 2 -> {
                    listarPerfiles();
                }
                case 3 -> {
                    listarSeries();
                }
                case 4 -> {
                    listarEpisodios();
                }
                case 5 -> {
                    listarHistoriales();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    break;
                }
            }
        } while (opcion != 0);
        session.close();
    }

    public static void listarUsuarios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Usuario> usuarios = session.createQuery("FROM Usuario", Usuario.class).list();
        System.out.println("Usuarios: ");
        for (Usuario u : usuarios) {
            System.out.println("\t" + u);
        }

        session.close();
    }

    public static void listarPerfiles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Perfil> perfiles = session.createQuery("FROM Perfil", Perfil.class).list();
        System.out.println("Perfiles: ");
        for (Perfil p : perfiles) {
            System.out.println("\t" + p);
        }
        session.close();
    }

    public static void listarSeries() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Serie> series = session.createQuery("FROM Serie ", Serie.class).list();
        System.out.println("Series: ");
        for (Serie s : series) {
            System.out.println("\t" + s);
        }
        session.close();
    }

    public static void listarEpisodios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Episodio> episodios = session.createQuery("FROM Episodio ", Episodio.class).list();
        System.out.println("Episodios: ");
        for (Episodio e : episodios) {
            System.out.println("\t" + e);
        }
        session.close();
    }

    public static void listarHistoriales() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Historial> historiales = session.createQuery("FROM Historial ", Historial.class).list();
        System.out.println("Historiales:");
        for (Historial h : historiales) {
            System.out.println("\t" + h);
        }
        session.close();
    }

    public static void insertar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int opcion = -1;
        do {
            opcion = opciones();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Introduce el nombre del Usuario:");
                    String nombre = sc.next();
                    System.out.print("Introduce el email del Usuario:");
                    String email = sc.next();

                    Usuario nuevoUsuario = new Usuario(email, nombre);
                    session.persist(nuevoUsuario);
                    tx.commit();
                    session.close();
                }
                case 2 -> {
                    System.out.print("Introduce el nombre del Perfil:");
                    String nombre = sc.next();

                    int edad = -1;
                    do {
                        System.out.print("Introduce la edad del Perfil:");
                        edad = Integer.parseInt(sc.next());
                    } while (!(edad > 0 && edad < 100));

                    listarUsuarios();
                    System.out.print("Introduce el número de Usuario del perfil:");
                    int id_usuario = Integer.parseInt(sc.next());
                    Usuario nuevoUsuario = session.find(Usuario.class, id_usuario);

                    Perfil nuevoPerfil = new Perfil(nombre, edad, nuevoUsuario);
                    session.persist(nuevoPerfil);
                    tx.commit();
                    session.close();

                }
                case 3 -> {
                    System.out.print("Introduce el título de la titulo:");
                    String titulo = sc.next();
                    System.out.print("Introduce el género de la titulo:");
                    String genero = sc.next();
                    System.out.print("Introduce la calificación de edad de la titulo:");
                    int calificion_edad = Integer.parseInt(sc.next());

                    Serie nuevaSerie = new Serie(titulo, genero, calificion_edad);
                    session.persist(nuevaSerie);
                    tx.commit();
                    session.close();
                }
                case 4 -> {
                    System.out.print("Introduce el título de la titulo:");
                    String titulo = sc.next();
                    sc.nextLine();
                    System.out.print("Introduce la duración del episodio(en minutos):");
                    int duracion = Integer.parseInt(sc.next());

                    listarSeries();
                    System.out.print("Introduce el id de la serie a la que pertenece el episodio:");
                    int id_serie = Integer.parseInt(sc.next());
                    Serie episodioSerie = session.find(Serie.class, id_serie);


                    Episodio nuevoEpisodio = new Episodio(titulo, duracion, episodioSerie);
                    session.persist(nuevoEpisodio);
                    tx.commit();
                    session.close();
                }
                case 5 -> {

                    boolean formato = false;
                    java.util.Date fechaReproduccionFormat = null;
                    Date fechaReproduccion = null;
                    while (!formato) {
                        System.out.print("Introduce la fecha de reproducción(Formato:yyyy-MM-dd):");
                        String fecha_reproduccion_String = sc.next();
                        if (fecha_reproduccion_String != null && !fecha_reproduccion_String.isEmpty()) {
                            try {
                                fechaReproduccionFormat = formatter.parse(fecha_reproduccion_String);
                                fechaReproduccion = new Date(fechaReproduccionFormat.getTime());
                                formato = true;
                            } catch (ParseException e) {
                                System.out.println("Error al formatear fecha reproducción: " + e.getMessage());
                            }

                        }
                    }

                    listarPerfiles();
                    System.out.print("Introduce el id del perfil:");
                    int id_perfil = Integer.parseInt(sc.next());
                    Perfil perfilHistorial = session.find(Perfil.class, id_perfil);

                    listarEpisodios();
                    System.out.print("Introduce el id del episodio:");
                    int id_episodio = Integer.parseInt(sc.next());
                    Episodio episodio = session.find(Episodio.class, id_episodio);

                    Historial nuevoHistorial = new Historial(fechaReproduccion, perfilHistorial, episodio);
                    session.persist(nuevoHistorial);
                    tx.commit();
                    session.close();

                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    session.close();
                    break;
                }
            }
        } while (opcion != 0);
    }

    public static void actualizar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int opcion = -1;
        do {
            opcion = opciones();
            switch (opcion) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    break;
                }

            }
        } while (opcion != 0);
    }

    public static void eliminar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int opcion = -1;
        do {
            opcion = opciones();
            switch (opcion) {
                case 1 -> {
                    listarUsuarios();
                    System.out.print("Introduce el id del Usuario que quieres borrar:");
                    int id_Usuario = Integer.parseInt(sc.next());
                    Usuario borrarUsuario = session.find(Usuario.class, id_Usuario);
                    if (borrarUsuario != null) {
                        session.remove(borrarUsuario);
                        tx.commit();
                        session.close();
                        System.out.println("Se ha borrado con exito.");
                    } else {
                        System.out.println("El Usuario no existe.");
                    }

                }
                case 2 -> {
                    listarPerfiles();
                    System.out.println("Introduce el id del Perfil que quieres borrar:");
                    int id_Perfil = Integer.parseInt(sc.next());
                    Perfil borrarPerfil = session.find(Perfil.class, id_Perfil);
                    if (borrarPerfil != null) {
                        session.remove(borrarPerfil);
                        tx.commit();
                        session.close();
                        System.out.println("Se ha borrado con exito.");
                    } else {
                        System.out.println("El Perfil no existe.");
                    }
                }
                case 3 -> {
                    listarSeries();
                    System.out.println("Introduce el id de la Serie que quieres borrar:");
                    int id_Serie = Integer.parseInt(sc.next());
                    Serie borrarSerie = session.find(Serie.class, id_Serie);
                    if (borrarSerie != null) {
                        session.remove(borrarSerie);
                        tx.commit();
                        session.close();
                        System.out.println("Se ha borrado con exito.");
                    } else {
                        System.out.println("La Serie no existe.");
                    }
                }
                case 4 -> {
                    listarEpisodios();
                    System.out.println("Introduce el id del Episodio que quieres borrar:");
                    int id_Episodio = Integer.parseInt(sc.next());
                    Episodio borrarEpisodio = session.find(Episodio.class, id_Episodio);
                    if (borrarEpisodio != null) {
                        session.remove(borrarEpisodio);
                        tx.commit();
                        session.close();
                        System.out.println("Se ha borrado con exito.");
                    } else {
                        System.out.println("El Episodio no existe.");
                    }
                }
                case 5 -> {
                    listarHistoriales();
                    System.out.println("Introduce el id del Historial que quieres borrar:");
                    int id_Historial = Integer.parseInt(sc.next());
                    Historial borrarHistorial = session.find(Historial.class, id_Historial);
                    if (borrarHistorial != null) {
                        session.remove(borrarHistorial);
                        tx.commit();
                        session.close();
                        System.out.println("Se ha borrado con exito.");
                    } else {
                        System.out.println("El Historial no existe.");
                    }
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    break;
                }

            }
        } while (opcion != 0);
    }
}
