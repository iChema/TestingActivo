package com.example.ichema.testingactivo;

import java.util.List;

public class Caso {
    private int imagen;
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha_ejecucion;
    private String status;
    private List<Prueba> pruebas;
    private int visitas;

    public Caso(int imagen, String nombre, int visitas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.visitas = visitas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVisitas() {
        return visitas;
    }

    public int getImagen() {
        return imagen;
    }
}
