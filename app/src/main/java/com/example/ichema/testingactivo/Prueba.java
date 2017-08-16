package com.example.ichema.testingactivo;

class Prueba {
    private int id;
    private String nombre;
    private String esperado;
    private String recibido;
    private String status;
    private long tiempo;
    private int imagen;
    private int visitas;

    public Prueba(int imagen, String nombre, int visitas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.visitas = visitas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEsperado() {
        return esperado;
    }

    public void setEsperado(String esperado) {
        this.esperado = esperado;
    }

    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public int getImagen() {
        return imagen;
    }


    public int getVisitas() {
        return visitas;
    }
}
