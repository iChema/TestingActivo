package com.example.ichema.testingactivo;

import java.util.List;

public class Caso {
    private int imagen;
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha_ejecucion;
    private boolean en_ejecucion;
    private String status;
    private List<Prueba> pruebas;

    public Caso(String nombre, String descripcion, String fecha_ejecucion, String status) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_ejecucion = fecha_ejecucion;
        this.status = status;
    }


    public boolean isEn_ejecucion() {
        return en_ejecucion;
    }

    public void setEn_ejecucion(boolean en_ejecucion) {
        this.en_ejecucion = en_ejecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() { return descripcion; }

    public String getFecha_ejecucion () { return fecha_ejecucion; }

    public String getStatus () { return status; }

    public int getId() {
        return id;
    }
}
