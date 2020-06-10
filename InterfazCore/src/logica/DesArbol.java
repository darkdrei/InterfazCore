/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;

/**
 *
 * @author dark
 */
public class DesArbol {
    private File file;
    private String nombre;

    @Override
    public String toString() {
        return "DesArbol{" + "file=" + file + ", nombre=" + nombre + '}';
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DesArbol(File file, String nombre) {
        this.file = file;
        this.nombre = nombre;
    }    
}
