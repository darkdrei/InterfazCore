/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author dark
 */
public class pactica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
//        try {
        // TODO code application logic here
        File folder = new File("/home/dark/proyectos/aaaaa");
            System.out.println("Esto es lo q hay ---> " + folder.exists());
            if (!folder.exists()) {
                folder.mkdirs();
            }
            fos = new FileOutputStream("/home/dark/proyectos/Modulos_test/Modulo1.zip");
            ZipInputStream zis = new ZipInputStream(new FileInputStream("/home/dark/proyectos/Modulos_test/Modulo1.zip"));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();


            zis.closeEntry();
            zis.close();
            ZipUtils.extract(new File("/home/dark/proyectos/Modulos_test/Modulo1.zip"),new File("/home/dark/proyectos/aaaaa"));
            System.out.println("Esto es lo q hay ---> " + folder.exists());
            System.out.println("Esto es lo q hay ---> " + folder.isDirectory());

//        System.out.println("Esto es lo q hay ---> " + folder.delete());
        deleteTemporal(folder);
        System.out.println("Esto es lo q hay ---> " + folder.exists());
        // ZipUtils.extract(new File("/home/dark/proyectos/Modulos_test/Modulo1.zip") , new File("/home/dark/Escritorio/Desde-Java"));
//        Vector<Integer> t = new Vector<>();
//        t.add(1);
//        t.add(1);
//        t.add(1);
//        t.add(1);
//        t.add(1);
//        t.add(1);
//        t.add(1);
//        Object v[] = getvector(3);
//        System.err.println(Arrays.asList(t).get(0).toArray());
//        if (v instanceof String[]){
//            System.err.println("Es una cadena "+((String[])v));
//            String r[] = (String[])v;
//            for (String r1 : r) {
//                System.err.println(r1);
//            }
//        }else if (v instanceof Integer[]){
//            System.err.println("Es un entero"+((Integer[])v));
//            Integer r[] = (Integer[])v;
//            for (int r1 : r) {
//                System.err.println(r1);
//            }
//        }else if (v instanceof Boolean[]){
//            System.err.println("Es una boleano  "+((Boolean[])v));
//            Boolean r[] = (Boolean[])v;
//            for (boolean r1 : r) {
//                System.err.println(r1);
//            }
//        }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(pactica.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException ex) {
//                Logger.getLogger(pactica.class.getName()).log(Level.SEVERE, null, ex);
//            }

    }

    public static void deleteTemporal(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteTemporal(sub);
            }
        }
        element.delete();
    }
}
