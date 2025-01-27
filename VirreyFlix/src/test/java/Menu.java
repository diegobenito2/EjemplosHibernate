import com.example.HibernateUtil;
import com.example.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        int opcion = -1;
        do {
            System.out.print("¿Qué desea hacer?: " +
                    "\n\tListar     -> 1" +
                    "\n\tInsertar   -> 2" +
                    "\n\tActualizar -> 3" +
                    "\n\tEliminar   -> 4" +
                    "\n\tSalir      -> 0" +
                    "\nElige una opción:");
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
                    "\nElige una opción:");
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
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        session.close();
    }

    public static void listarPerfiles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Perfil> perfiles = session.createQuery("FROM Perfil", Perfil.class).list();
        for (Perfil p : perfiles) {
            System.out.println(p);
        }
        session.close();
    }

    public static void listarSeries() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Serie> series = session.createQuery("FROM Serie ", Serie.class).list();
        for (Serie s : series) {
            System.out.println(s);
        }
        session.close();
    }

    public static void listarEpisodios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Episodio> episodios = session.createQuery("FROM Episodio ", Episodio.class).list();
        for (Episodio e : episodios) {
            System.out.println(e);
        }
        session.close();
    }

    public static void listarHistoriales() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Historial> historiales = session.createQuery("FROM Historial ", Historial.class).list();
        for (Historial h : historiales) {
            System.out.println(h);
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
                    System.out.println("Introduce el nombre del Usuario:");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce el email del Usuario:");
                    String email = sc.nextLine();

                    Usuario nuevoUsuario = new Usuario(email, nombre);
                    session.persist(nuevoUsuario);
                }
                case 2 -> {
                    System.out.println("Introduce el nombre del Perfil:");
                    String nombre = sc.nextLine();

                    int edad = -1;
                    do {
                        System.out.println("Introduce la edad del Perfil:");
                        edad = Integer.parseInt(sc.nextLine());
                    } while (!(edad > 0 && edad < 100));

                    System.out.println("Usuarios: ");
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
                    System.out.print("Introduce la duración del episodio(en minutos):");
                    int duracion = Integer.parseInt(sc.next());

                    Episodio nuevoEpisodio = new Episodio(titulo, duracion);
                    session.persist(nuevoEpisodio);
                    tx.commit();
                    session.close();
                }
                case 5 -> {
                    System.out.print("Introduce la fecha de reproducción:");
                    String fecha_reproduccion_String = sc.next();
                    Date fechaReproduccionFormat = null;
                    if (fecha_reproduccion_String != null && !fecha_reproduccion_String.isEmpty()) {
                        try {
                            fechaReproduccionFormat = (Date) formatter.parse(fecha_reproduccion_String);
                        } catch (ParseException e) {
                            System.out.println("Error al formatear fecha Inicio ubicacion: " + e.getMessage());
                        }
                    }
                    System.out.println("Perfiles:");
                    listarPerfiles();
                    System.out.print("Introduce el id del perfil:");
                    int id_perfil = Integer.parseInt(sc.next());
                    Perfil perfilHistorial = session.find(Perfil.class, id_perfil);

                    System.out.println("Episodios:");
                    listarEpisodios();
                    System.out.print("Introduce el id del episodio:");
                    int id_episodio=Integer.parseInt(sc.next());
                    Episodio episodio= session.find(Episodio.class,id_episodio);

                    Historial nuevoHistorial = new Historial(fechaReproduccionFormat, perfilHistorial, episodio);
                    session.persist(nuevoHistorial);
                    tx.commit();
                    session.close();

                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    break;
                }
            }
        } while (opcion != 0);
        tx.commit();
        session.close();
    }

    public static void actualizar() {
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
}
