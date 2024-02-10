package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Consola {

    //Constructor
    private Consola(){};

    //Métodos
    public static void mostrarMenu(){
        for (Opcion opcion: Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.println("\nElige una opción: ");
            ordinalOpcion = Entrada.entero();
        } while (!(ordinalOpcion >= 0 && ordinalOpcion <= (Opcion.values().length - 1)));
        return Opcion.values()[ordinalOpcion];
    }

    public static Huesped leerHuesped() {
        Huesped huesped = null;
        String nombre;
        String telefono;
        String correo;
        String dni;

        do {
            System.out.print("Introduce el nombre del huésped: ");
            nombre = Entrada.cadena();
        } while (nombre.trim().isEmpty());

        do {
            System.out.print("Introduce el DNI del huésped: ");
            dni = Entrada.cadena();
        } while (dni.trim().isEmpty());

        do {
            System.out.print("Introduce el correo del huésped: ");
            correo = Entrada.cadena();
        } while (correo.trim().isEmpty());

        do {
            System.out.print("Introduce el teléfono del huésped: ");
            telefono = Entrada.cadena();
        } while (telefono.trim().isEmpty());

        String mensaje = "Introduce la fecha de nacimiento del huésped (%s): ";
        LocalDate fechaNacimiento = leerFecha(mensaje);

        huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);

        return new Huesped(huesped);
    }

    public static Huesped leerClientePorDni() {

        Huesped huesped = null;
        String nombre = "Huésped Ficticio";
        String telefono = "666666666";
        String correo = "HuspedFicticio@gmail.com";
        String dni = null;
        LocalDate fechaNacimiento = LocalDate.now();

        //try {
            do {
                System.out.print("Introduce el DNI del huésped: ");
                dni = Entrada.cadena();
            } while (dni.isEmpty());

            huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
       /* }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }*/
        return new Huesped(huesped);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.printf(mensaje, Huesped.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                fechaValida = false;
            }
        } while(!fechaValida);
        return fecha;
    }

    public static Habitacion leerHabitacion() {

        Habitacion habitacion = null;
        int planta;
        int puerta;
        double precio;

        do {
            System.out.print("Introduce la planta de la habitación: ");
            planta = Entrada.entero();
        } while (planta < Habitacion.MIN_NUMERO_PLANTA ||
                planta > Habitacion.MAX_NUMERO_PLANTA);

        do {
            System.out.print("Introduce la puerta de la habitación: ");
            puerta = Entrada.entero();
        } while (puerta < Habitacion.MIN_NUMERO_PUERTA ||
                puerta > Habitacion.MAX_NUMERO_PUERTA);

        do {
            System.out.print("Introduce el precio de la habitación: ");
            precio = Entrada.realDoble();
        } while (precio < Habitacion.MIN_PRECIO_HABITACION ||
                precio > Habitacion.MAX_PRECIO_HABITACION);

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        habitacion = new Habitacion(planta, puerta, precio, tipoHabitacion);

        return new Habitacion(habitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador() {

        Habitacion habitacion = null;
        int planta;
        int puerta;
        double precio = 70.0;
        TipoHabitacion tipoHabitacion = TipoHabitacion.SIMPLE;

        do {
            System.out.print("Introduce la planta de la habitación: ");
            planta = Entrada.entero();
        } while (planta < Habitacion.MIN_NUMERO_PLANTA ||
                planta > Habitacion.MAX_NUMERO_PLANTA);

        do {
            System.out.print("Introduce la puerta de la habitación: ");
            puerta = Entrada.entero();
        }  while (puerta < Habitacion.MIN_NUMERO_PUERTA ||
                puerta > Habitacion.MAX_NUMERO_PUERTA);

        habitacion = new Habitacion(planta, puerta, precio, tipoHabitacion);

        return new Habitacion(habitacion);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        int ordinalHabitacion;
        for (TipoHabitacion habitacion: TipoHabitacion.values()) {
            System.out.println(habitacion.ordinal() + ".- " + habitacion);
        }
        do {
            //System.out.println(Arrays.toString(TipoHabitacion.values()));
            System.out.println("\nElige una opción: ");
            ordinalHabitacion = Entrada.entero();
        } while (!(ordinalHabitacion >= 0 && ordinalHabitacion <= (TipoHabitacion.values().length - 1)));
        return TipoHabitacion.values()[ordinalHabitacion];
    }

    public static Regimen leerRegimen(){
        int ordinalRegimen;
        for (Regimen regimen: Regimen.values()) {
            System.out.println(regimen.ordinal() + ".- " + regimen);
        }
        do {
            //System.out.println(Arrays.toString(Regimen.values()));
            System.out.println("\nElige un régimen: ");
            ordinalRegimen = Entrada.entero();
        } while (!(ordinalRegimen >= 0 && ordinalRegimen <= (Regimen.values().length - 1)));
        return Regimen.values()[ordinalRegimen];
    }

    public static Reserva leerReserva() {

        Reserva reserva = null;
        Huesped huesped = null;
        Habitacion habitacion = null;
        Regimen regimen = null;
        int numeroPersonas;

        //try {

            huesped = leerHuesped();
            habitacion = leerHabitacion();
            regimen = leerRegimen();

            String mensaje = "Introduce la fecha de inicio de la reserva (%s): ";
            LocalDate fechaInicioReserva = leerFecha(mensaje);

            String mensaje2 = "Introduce la fecha de fin de la reserva (%s): ";
            LocalDate fechaFinReserva = leerFecha(mensaje2);

            System.out.print("Introduce el número de personas: ");
            numeroPersonas = Entrada.entero();

            reserva = new Reserva(huesped, habitacion, regimen, fechaInicioReserva,
                    fechaFinReserva, numeroPersonas);
        /*}
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }*/
        return new Reserva(reserva);
    }

}
