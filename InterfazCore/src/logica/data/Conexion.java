/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.data;

import java.sql.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Carotech
 */
public class Conexion {
    public static int num=0;
    public static boolean BANDERA =false; 
    private static Conexion conexion = new Conexion();
    private static Connection conex;
    private static Statement stat;
    //private static String ruta ="/home/dark/Dropbox/Restaurante/origam.db";
    private static String ruta ="/home/dark/Dropbox/Core/git/InterfazIntegradora/data.db";
    
    private Conexion(){
        abrirConexion();
    }
    
    public void abrirConexion(){
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar
            //String ruta = "/home/dreicon88/Dropbox/Restaurante/Restaurante-V2-2/origami.db"; //especificamos la ruta de la base
            //String ruta = "/home/dark/Dropbox/Restaurante/V2/Origami2/src/logica/db/origami.db";
            //String ruta = "H:\\windows 10\\Origami2\\src\\logica\\db\\origami.db"; //especificamos la ruta de la base
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\\\db\\\\origami.db";
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta="C:\\Users\\CTesting\\Pictures\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta ="/home/carotech/Descargas/Origami2(Backup f)/src/logica/db/origami.db";
            //String ruta ="C:\\Users\\dreicon\\Desktop\\Restaurante\\V2\\Origami2(Backup f)\\src\\logica\\db\\origami.db";
            //String ruta ="C:\\Users\\hp 4204LA\\Desktop\\origami\\origami.db";
            //String ruta ="C:\\Users\\Carotech\\Desktop\\Origami2(Backup II-11)\\Origami2(Backup f)\\src\\logica\\db\\origami.db";
           //ruta ="/home/dark/Dropbox/Restaurante/origam.db";}
            File base = new File(ruta); //la declaramos como un archivo
            System.err.println("*************************  "+base.exists());
            if (base.exists()) {       //si la base existe
                System.err.println("jdbc:sqlite:" + ruta);
                conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                stat = conex.createStatement();              
                BANDERA = true;
                cerrarConexion();
               //JOptionPane.showMessageDialog(null, "La base existe, y se ha conectado exitosamente!!");
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos no existe o no se encuentra en la ruta especificada.");
                BANDERA = false;
               System.exit(0);
            }
        } catch (Exception e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e+"  se exploto en el inicio");//hubo un error
        }
    }
    
    public static Connection getConnecion(){
        return conex;
    }
    
    public static Statement getStamento(){
        return stat;
    }
    
    public static void setStamento(Statement t){
        stat=t;
    }
    
    public static void setBandera(boolean be){
        BANDERA=be;
    }
    
    public static boolean getBandera(){
        return BANDERA;
    }
    
    public static void cerrarConexion() throws SQLException{
        stat.close();
        conex.close();
    }
    
    public static void playConexion() throws SQLException{
        conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
    }
    
}
