package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Comparator;
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
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> consultarDisponibilidad(
                    leerTipoHabitacion(),
                    leerFecha("Introduce la fecha de inicio de reserva (%s): "),
                    leerFecha("Introduce la fecha de fin de reserva (%s): "));

        }
    }

    private static void insertarHuesped(){
        String mensaje = "Insertar huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerHuesped();
            huespedes.insertar(huesped);
            System.out.println("Huésped insertado correctamente.");
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped(){
        String mensaje = "Buscar huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerClientePorDni();
            Huesped huesped1 = huespedes.buscar(huesped);
            if (huesped1 != null) {
                System.out.println("El huésped buscado es: " + huesped1);
            } else {
                System.out.println("No existe ningún huésped con dicho DNI.");
            }
        }catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHuesped(){
        String mensaje = "Borrar huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = leerClientePorDni();
            Huesped huesped1 = huespedes.buscar(huesped);
            huespedes.borrar(huesped1);
            System.out.println("Huésped borrado correctamente.");
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
            System.out.println("No hay huéspedes que mostrar.");
        }
    }

    private static void insertarHabitacion(){
        String mensaje = "Insertar habitación";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacion();
            habitaciones.insertar(habitacion);
            System.out.println("Habitación insertada correctamente.");
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHabitacion(){
        String mensaje = "Buscar habitación";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacionPorIdentificador();
            Habitacion habitacion1 = habitaciones.buscar(habitacion);
            if (habitacion1 != null) {
                System.out.println("La habitación buscada es: " + habitacion1);
            } else {
                System.out.println("La habitación buscada no existe.");
            }
        }catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion(){
        String mensaje = "Borrar habitacción";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = leerHabitacionPorIdentificador();
            Habitacion habitacion1 = habitaciones.buscar(habitacion);
            habitaciones.borrar(habitacion1);
            System.out.println("Habitación borrada correctamente.");
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

    private static void insertarReserva(){
        String mensaje = "Insertar reserva";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Reserva reserva = leerReserva();
            Habitacion habitacionDisponible = consultarDisponibilidad(reserva.getHabitacion().getTipoHabitacion(),
                    reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
            if (!(habitacionDisponible==null)) {
                reservas.insertar(reserva);
                System.out.println("Reserva insertada correctamente.");
            } else {
                System.out.println("El tipo de habitación a reservar NO está disponible.");
            }
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarReservas(Huesped huesped){

        if(!(huesped ==null)){
            Reserva[] reservas1 = reservas.getReservas(huesped);

            if (reservas1.length > 0) {
                int i = 0;
                for (Reserva reserva2 : reservas1) {
                    System.out.println(i + ".- " + reserva2);
                    i++;
                }
            } else {
                System.out.println("No hay reservas que listar.");
            }
        } else {
            System.out.println("El huésped no existe.");
        }
    }

    private static void listarReservas(TipoHabitacion tipoHabitacion){

        Reserva[] reservas1 = reservas.getReservas(tipoHabitacion);
        if (!(reservas1==null)){
            if (reservas1.length > 0) {
                int i = 0;
                for (Reserva reserva2 : reservas1) {
                    System.out.println(i + ".- " + reserva2);
                    i++;
                }
            } else {
                System.out.println("No hay reservas que listar.");
            }
        } else {
            System.out.println("No existe reserva.");
        }
    }

    private static Reserva[] getReservasAnulables(Reserva[] reservasAAnular){
        Reserva[] reservasAnulables = new Reserva[reservasAAnular.length];
        int i = 0;
        for (Reserva reserva2 : reservasAAnular) {
            if(reserva2.getFechaInicioReserva().isAfter(LocalDate.now())){
                reservasAnulables[i] = reserva2;
                i++;
            }
        }
        return reservasAnulables;
    }

    private static void anularReserva(){
        char confReserva = 'S';
        try {
            Huesped huesped = leerClientePorDni();
            Huesped huesped1 = huespedes.buscar(huesped);
            if (huesped1 != null) {
                Reserva[] reservas1 = reservas.getReservas(huesped);
                reservas1 = getReservasAnulables(reservas1);
                if (reservas1.length > 0) {
                    //listarReservas(huesped1);
                    System.out.println("Elija la reserva que desea anular.");
                    int numReserva = Entrada.entero();

                    if (reservas1.length == 1){
                        System.out.println("Confirma que desea anular la reserva (S/N)?");
                        confReserva = Entrada.caracter();
                    }
                    if (confReserva=='S'){
                        reservas.borrar(reservas1[numReserva]);
                    }
                } else {
                    System.out.println("No hay reservas que se puedan anular.");
                }
            } else {
                System.out.println("No existe ningún huésped con dicho DNI.");
            }
        }catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarReservas(){
        Reserva[] listaReserva = reservas.get();
        if (listaReserva.length > 0) {
            for (Reserva reserva1 : listaReserva) {
                System.out.println(reserva1);
            }
        } else {
            System.out.println("No hay reservas que mostrar.");
        }
    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion,
                                                LocalDate fechaInicioReserva,
                                                LocalDate fechaFinReserva){
        try {

            if (tipoHabitacion == null) {
                throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un tipo de habitación nulo.");
            }
            if (fechaInicioReserva.isBefore(LocalDate.now())) {
                throw new NullPointerException("ERROR: La fecha de inicio no debe ser anterior al día de hoy.");
            }
            if (!fechaFinReserva.isAfter(fechaInicioReserva)) {
                throw new NullPointerException("ERROR: La fecha de fin de reserva debe ser posterior a la fecha de inicio de reserva.");
            }


            Habitacion[] habitacionesTipoSolicitado = null;
            int i = 0;
            for (Habitacion habitacion : habitaciones.get()){
                if(!(habitacion.getTipoHabitacion().equals(tipoHabitacion))) {
                    habitacionesTipoSolicitado[i] = habitacion;
                    i++;
                }
            }

            if (habitacionesTipoSolicitado==null){
                return null;
            } else {
                for (Habitacion habitacion : habitacionesTipoSolicitado){
                    Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacion);
                    int numElementosNoNulos = (int) Arrays.stream(reservasFuturas).filter(Objects::nonNull).count();
                    if (numElementosNoNulos==0) {
                        return habitacion;
                    } else {
                        Arrays.sort(reservasFuturas, 0, numElementosNoNulos,
                                Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                        if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())){
                            return habitacion;
                        } else {
                            Arrays.sort(reservasFuturas, 0, numElementosNoNulos,
                                    Comparator.comparing(Reserva::getFechaInicioReserva));

                            if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())){
                                return habitacion;
                            } else {
                                Habitacion habitacionDisponible = null;
                                boolean tipoHabitacionEncontrada = false;
                                for(int j=1;j<reservasFuturas.length && !tipoHabitacionEncontrada;j++)
                                {
                                    if (reservasFuturas[j]!=null && reservasFuturas[j-1]!=null)
                                    {
                                        if(fechaInicioReserva.isAfter(reservasFuturas[j-1].getFechaFinReserva()) &&
                                                fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva()))
                                        {
                                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                            tipoHabitacionEncontrada = true;
                                        }
                                    }
                                }
                                return habitacionDisponible;
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
