package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;

import javax.naming.OperationNotSupportedException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import static org.iesalandalus.programacion.reservashotel.vista.Consola.*;

public class MainApp {

    // Se crean los atributos con su visibilidad adecuada
    public static final int CAPACIDAD = 45;
    private static Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private static Reservas reservas = new Reservas(CAPACIDAD);
    private static Huespedes huespedes = new Huespedes(CAPACIDAD);

    // Main
    public static void main(String[] args) {

        Opcion opcion;
        do {
            mostrarMenu();
            opcion = elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);



    }

    private static void ejecutarOpcion(Opcion opcion){
        switch (opcion) {
            case SALIR -> System.out.println("Hasta luego!!!");
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            /*case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> consultarDisponibilidad();
             */
        }
    }

    private static void insertarHuesped(){
        String mensaje = "Insertar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerHuesped();
            huespedes.insertar(huesped);
            System.out.println("Hu�sped insertado correctamente.");
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped(){
        String mensaje = "Buscar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerClientePorDni();
            Huesped huesped1 = huespedes.buscar(huesped);
            if (huesped1 != null) {
                System.out.println("El hu�sped buscado es: " + huesped1);
            } else {
                System.out.println("No existe ning�n hu�sped con dicho DNI.");
            }
        }catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHuesped(){
        String mensaje = "Borrar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerClientePorDni();
            Huesped huesped1 = huespedes.buscar(huesped);
            huespedes.borrar(huesped1);
            System.out.println("Hu�sped borrado correctamente.");
        } catch (OperationNotSupportedException | IllegalArgumentException |
                 NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHuespedes(){
        Huesped[] listaHuespedes = huespedes.get();
        if (listaHuespedes.length > 0) {
            for (Huesped huesped : listaHuespedes) {
                System.out.println(huesped);
            }
        } else {
            System.out.println("No hay hu�spedes que mostrar.");
        }
    }

    private static void insertarHabitacion(){
        String mensaje = "Insertar habitaci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacion();
            habitaciones.insertar(habitacion);
            System.out.println("Habitaci�n insertada correctamente.");
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHabitacion(){
        String mensaje = "Buscar habitaci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacionPorIdentificador();
            Habitacion habitacion1 = habitaciones.buscar(habitacion);
            if (habitacion1 != null) {
                System.out.println("La habitaci�n buscada es: " + habitacion1);
            } else {
                System.out.println("La habitaci�n buscada no existe.");
            }
        }catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion(){
        String mensaje = "Borrar habitacci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacionPorIdentificador();
            Habitacion habitacion1 = habitaciones.buscar(habitacion);
            habitaciones.borrar(habitacion1);
            System.out.println("Habitaci�n borrada correctamente.");
        } catch (OperationNotSupportedException | IllegalArgumentException |
                NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones(){
        Habitacion[] listaHabitacion = habitaciones.get();
        if (listaHabitacion.length > 0) {
            for (Habitacion habitacion : listaHabitacion) {
                System.out.println(habitacion);
            }
        } else {
            System.out.println("No hay habitaciones que mostrar.");
        }
    }
}
