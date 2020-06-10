/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.data;

/**
 *
 * @author dark
 */
public class Componente extends BaseDAO{
    private GestionVector atributos;
    private GestionVector columna;
    private GestionVector parametros;
    private int id;
    private String nombre;
    private String descripcion;
    private String autor;

    public Componente(GestionVector atributos, GestionVector columna, GestionVector parametros, String nombre, String descripcion, String autor) {
        this.atributos = atributos;
        this.columna = columna;
        this.parametros = parametros;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
    }

    public Componente(GestionVector atributos, GestionVector columna, GestionVector parametros, int id, String nombre, String descripcion, String autor) {
        this.atributos = atributos;
        this.columna = columna;
        this.parametros = parametros;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
    }

    public GestionVector getAtributos() {
        return atributos;
    }

    public void setAtributos(GestionVector atributos) {
        this.atributos = atributos;
    }

    public GestionVector getColumna() {
        return columna;
    }

    public void setColumna(GestionVector columna) {
        this.columna = columna;
    }

    public GestionVector getParametros() {
        return parametros;
    }

    public void setParametros(GestionVector parametros) {
        this.parametros = parametros;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
