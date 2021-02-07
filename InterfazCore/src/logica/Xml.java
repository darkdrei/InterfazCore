/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author dark
 */
public class Xml implements Cloneable {

    private Autor autor;
    private Cuerpo cuerpo;
    private Ruta ruta;
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private Status status;

    public Xml(Autor autor, Cuerpo cuerpo, Ruta ruta, Status status) {
        this.autor = autor;
        this.cuerpo = cuerpo;
        this.ruta = ruta;
        this.id = count.incrementAndGet();
        this.status = status;
    }

    public Xml() {
        this.autor = new Autor();
        this.cuerpo = new Cuerpo();
        this.ruta = new Ruta();
        this.id = count.incrementAndGet();
        this.status = new Status(true);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Cuerpo getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Cuerpo cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public ArrayList<String> getParametros() {
        return this.getCuerpo().getParametros();
    }

    public void addParametro(String parametro) {
        this.getCuerpo().getParametros().add(parametro);
    }

    public void setStatus(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class Ruta {

        private String nombre;
        private String direccion;

        public Ruta(String nombre, String ruta) {
            this.nombre = nombre;
            this.direccion = ruta;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public Ruta() {
            this.nombre = "";
            this.direccion = "";
        }

        @Override
        public String toString() {
            return "Ruta{" + "nombre=" + nombre + ", direccion=" + direccion + '}';
        }

    }

    public class Autor {

        private String nombre;
        private String descripcion;
        private String version;

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

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Autor(String nombre, String descripcion, String version) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.version = version;
        }

        public Autor() {
            this.nombre = "";
            this.descripcion = "";
            this.version = "";
        }

        @Override
        public String toString() {
            return "Autor{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", version=" + version + '}';
        }

    }

    public class Cuerpo {

        private String[] tipo_datos;
        private int columnas;
        private ArrayList<String> parametros;
        private String main;

        public Cuerpo(String[] tipo_datos, int columnas, ArrayList<String> parametros, String main) {
            this.tipo_datos = tipo_datos;
            this.columnas = columnas;
            this.parametros = parametros;
            this.main = main;
        }

        public Cuerpo() {
            this.tipo_datos = new String[10];
            this.columnas = 0;
            this.parametros = new ArrayList<>();
            this.main = "";
        }

        public String[] getTipo_datos() {
            return tipo_datos;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public void setTipo_datos(String[] tipo_datos) {
            this.tipo_datos = tipo_datos;
        }

        public int getColumnas() {
            return columnas;
        }

        public void setColumnas(int columnas) {
            this.columnas = columnas;
        }

        public ArrayList<String> getParametros() {
            return parametros;
        }

        public void setParametros(ArrayList<String> parametros) {
            this.parametros = parametros;
        }

        @Override
        public String toString() {
            return "Cuerpo{" + "tipo_datos=" + tipo_datos + ", columnas=" + columnas + ", parametros=" + parametros + '}';
        }

    }

    public class Status {

        private boolean active;

        public Status(boolean active) {
            this.active = active;
        }

        public boolean getActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public String toString() {
            return "Status{" + "active=" + active + '}';
        }

    }

    public String toStringId() {
        return "\n ------ Pluguin ---> " + this.getId();
    }

    @Override
    public String toString() {
        String info = "";
        info += "\n ------ Pluguin ---> " + this.getId();
        info += "\n ------ Autor ------";
        info += "\nNombre  : " + this.getAutor().getNombre();
        info += "\nVersion : " + this.getAutor().getVersion();
        info += "\nRuta : " + this.getAutor().getVersion();
        info += "\nDescripcion : ";
        info += "\n" + this.getAutor().getDescripcion();
        info += "\n";
        info += "\nClase principal : ";
        info += "\n" + this.getCuerpo().getMain();
        info += "\n";
        info += "\nEstatus";
        info += "\n" + this.getStatus().getActive();
        info += "\n";
        info += "\n*** Parametros : ";
        for (String s : this.getParametros()) {
            info += "\n\t" + s;
        }
        info += "\n*** Tipos de datos de muestra : ";
        for (String s : this.getCuerpo().getTipo_datos()) {
            info += "\n\t" + s;
        }
        return info;
    }
}
