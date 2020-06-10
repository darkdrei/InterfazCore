/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import logica.data.ZipUtils;

/**
 * Esta clase encarga de las operaciones de manipulacion de el archivo Zip
 *
 * @author dark
 */
public class Zip implements Lector {

    private ZipFile zf;
    private String ruta_entrada;
    private String ruta_salida;
    private int size_buffer;

    private Vector<Contenido> files;

    public Zip() {
        ruta_entrada = "/home/dark/Escritorio/archivo1.zip";
        ruta_salida = "/home/dark/Escritorio/archivo1.zip";
        this.size_buffer = 8192;
        this.files = new Vector<>();
    }

    public Zip(File f) throws IOException {
        ruta_entrada = "/home/dark/Escritorio/archivo1.zip";
        ruta_salida = "/home/dark/Escritorio/archivo1.zip";
        this.size_buffer = 8192;
        this.files = new Vector<>();
    }

    public ZipFile getZf() {
        return zf;
    }

    public void setZf(ZipFile zf) {
        this.zf = zf;
    }

    public File getContenidoTipo(int i) {
        for (Contenido file : files) {
            if (file.getTipo() == i) {
                try {
                    return file.mostrarArchivo();
                } catch (IOException ex) {
                    return null;
                }
            }
        }
        return null;
    }

    public Vector<Contenido> getFiles() {
        return files;
    }

    public void setFiles(Vector<Contenido> files) {
        this.files = files;
    }

    public int getFilesContenido() {
        return this.getFiles().size();
    }

    public Contenido getContenidoFiles(int p) {
        return getFiles().get(p);
    }

    public String getRuta_entrada() {
        return ruta_entrada;
    }

    public void setRuta_entrada(String ruta_entrada) {
        this.ruta_entrada = ruta_entrada;
    }

    public String getRuta_salida() {
        return ruta_salida;
    }

    public void setRuta_salida(String ruta_salida) {
        this.ruta_salida = ruta_salida;
    }

    public int getSize_buffer() {
        return size_buffer;
    }

    public void setSize_buffer(int size_buffer) {
        this.size_buffer = size_buffer;
    }

    @Override
    public boolean validarArchivo(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarArchivo(File f, String ext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File[] getFiles(File f) {
        OSValidator os = new OSValidator();
        String path = "";
        if (os.getOS().equals("win")) {
            path = "C://Interfaz//temporal";
        } else {
            path = "/home/dark/proyectos/aaaaa";
        }
        File folder = new File(path);
        System.out.println("Esto es lo q hay ---> " + folder.exists());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        ZipUtils.extract(f, folder);
        return folder.listFiles();
    }

    @Override
    public File[] getFiles(String f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public File extraerArchivo(ZipEntry entry, InputStream is) throws IOException {
        String exractedFile = this.getRuta_salida() + entry.getName();
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(exractedFile);
            final byte[] buf = new byte[this.getSize_buffer()];
            int read = 0;
            int length;
            while ((length = is.read(buf, 0, buf.length)) >= 0) {
                fos.write(buf, 0, length);
            }
        } catch (IOException ioex) {
            fos.close();
        }
        return null;
    }

    @Override
    public void getsFiles(File f) {
        System.out.println("este es el archivo " + f);
        ZipFile file = null;
        try {
            file = new ZipFile(f);
            Enumeration<? extends ZipEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                System.err.println(entry);
                System.out.println("Esta es el tipo de archivo : " + (entry.getName().split("\\.")[1]) + " " + ((entry.getName().split("\\.")[1]).equals("jar")));
                files.add(new Contenido(entry, file.getInputStream(entry), (entry.getName().split("\\.")[1]).equals("jar") ? 2 : 1));
            }
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Object[] validarArchivoGrup(File f) {
        ZipFile file = null;
        try {
            file = new ZipFile(f);
            if (!file.getName().split("\\.")[1].toUpperCase().equals("ZIP")) {
                return new java.lang.Object[]{false, "El modulo debe ser ZIP."};
            }
            if (file.stream().count() != 2) {
                return new java.lang.Object[]{false, "El modulo debe estar compuesto por 2 archivos."};
            }
            Enumeration<? extends ZipEntry> entries = file.entries();
            int c = 0;
            String res = "";
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                System.out.println(">>>===>> " + entry.toString().split("\\.")[1].equals("jar") + "   " + (entry.toString().split("\\.")[1].equals("xml") || entry.toString().split("\\.")[1] == "jar"));
                c += entry.getName().split("\\.")[1].equals("xml") || entry.getName().split("\\.")[1].equals("jar") ? 1 : 0;
                res += "\n" + entry.getName().split("\\.")[1];
            }
            if (c != 2) {
                return new java.lang.Object[]{false, "El modulo debe estar compuesto por 2 archivos.\nEl existente solo contiene : " + res};
            }
            return new java.lang.Object[]{true};
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new java.lang.Object[]{false, "Error en el archivo."};
    }

    public class Contenido {

        private ZipEntry file_respuesta;
        private InputStream file_existente;
        private int tipo;

        public File mostrarArchivo() throws IOException {
            String exractedFile = "/home/dark/Escritorio/Desde-Java/temporal" + file_respuesta.getName();
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(exractedFile);
                final byte[] buf = new byte[getSize_buffer()];
                int read = 0;
                int length;

                while ((length = file_existente.read(buf, 0, buf.length)) >= 0) {
                    fos.write(buf, 0, length);
                }

            } catch (IOException ioex) {
                fos.close();
            }
            return new File(exractedFile);
        }

        public Contenido(ZipEntry file_respuesta, InputStream file_existente) {
            this.file_respuesta = file_respuesta;
            this.file_existente = file_existente;
        }

        /**
         *
         * @param file_respuesta : ruta de carpeta donde se colocara el archivo
         * @param file_existente : ruta del archivo actualmente
         * @param p : tipo de archivo sea jar ó xml, 1 xml y 2 jar
         */
        public Contenido(ZipEntry file_respuesta, InputStream file_existente, int p) {
            this.file_respuesta = file_respuesta;
            this.file_existente = file_existente;
            this.tipo = p;
        }

        public ZipEntry getFile_respuesta() {
            return file_respuesta;
        }

        public void setFile_respuesta(ZipEntry file_respuesta) {
            this.file_respuesta = file_respuesta;
        }

        public InputStream getFile_existente() {
            return file_existente;
        }

        public void setFile_existente(InputStream file_existente) {
            this.file_existente = file_existente;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            try {
                return "Contenido{" + "file_respuesta=" + file_respuesta + ", file_existente=" + mostrarArchivo() + ", tipo=" + tipo + '}';
            } catch (IOException ex) {
                Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }
    }
}
