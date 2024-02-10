package org.iesalandalus.programacion.reservashotel.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("SOLO_ALOJAMIENTO", 0),
    ALOJAMIENTO_DESAYUNO("ALOJAMIENTO_DESAYUNO", 15),
    MEDIA_PENSION("MEDIA_PENSION", 30),
    PENSION_COMPLETA("PENSION_COMPLETA", 50);

    // Se crean los atributos con su visibilidad adecuada
    private String descripcion;
    private double incrementoPrecio;

    //Constructor
    Regimen(String descripcion, double incrementoPrecio) {
        this.descripcion = descripcion;
        this.incrementoPrecio = incrementoPrecio;
    }

    //Método de acceso
    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    //Método toString
    @Override
    public String toString() {
        return String.format("Régimen: %s, incremento precio: %s",
                this.descripcion, getIncrementoPrecio());
    }
}
