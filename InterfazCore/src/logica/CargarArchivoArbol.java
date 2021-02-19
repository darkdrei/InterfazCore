

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import core.Core;
import javax.swing.ImageIcon;

import bibliothek.gui.dock.common.action.CButton;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author dark
 */
public class CargarArchivoArbol extends CButton {

    private CodePanel panel;
    private Core base;
    DefaultMutableTreeNode carpetaRaiz = new DefaultMutableTreeNode("Carpeta");
    DefaultTreeModel modelo = new DefaultTreeModel(carpetaRaiz);
    private ArrayList<DesArbol> nodos = new ArrayList<>();
    OSValidator os;
    
    public CargarArchivoArbol(CodePanel panel) {
        this.panel = panel;
        setText("Abrir archivo");
        setTooltip("Cargado de archivo.");
        Image image = null;
        
        try {
            if(os.getOS().equals("win")){
                File sourceimage = new File("src\\ima\\copy.png");
                image = ImageIO.read(sourceimage);
            }else{
                File sourceimage = new File("src/ima/copy.png");
                image = ImageIO.read(sourceimage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image);
        setIcon(icon);
    }

    public CargarArchivoArbol(Core panel) {
        this.base = panel;
        setText("Abrir archivo");
        setTooltip("Cargado de archivo");
        Image image = null;

        try {
            if(os.getOS().equals("win")){
                File sourceimage = new File("src\\ima\\copy.png");
                image = ImageIO.read(sourceimage);
            }else{
                File sourceimage = new File("src/ima/copy.png");
                image = ImageIO.read(sourceimage);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image);
        setIcon(icon);
    }

    @Override
    protected void action() {
        //panel.copy();
        //create the root node
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "java");
        int res = fc.showOpenDialog(this.getBase());
        if (res == JFileChooser.APPROVE_OPTION) {
            //System.out.println(fc.getSelectedFile().getAbsolutePath());
            File dir = new File(fc.getSelectedFile().getAbsolutePath());
            String archivos[] = dir.list();
            System.err.println("Se exploto la vaina ---->>>> " +archivos.length);
            leerDirectorio(archivos, fc.getSelectedFile().getAbsolutePath(),carpetaRaiz,0);
        }
        this.getBase().getTree().setModel(modelo);
    }

    public CodePanel getPanel() {
        return panel;
    }

    public void setPanel(CodePanel panel) {
        this.panel = panel;
    }

    public Core getBase() {
        return base;
    }

    public void setBase(Core base) {
        this.base = base;
    }

    public void leerDirectorio(String[] r, String path,DefaultMutableTreeNode padre,int i) {
        System.out.println("Componente es -> "+ r);
        if(r == null){
            return;
        }
        for (String f : r) {
            if (f.contains(".")) {
                String ruta = path + "/" + f;
                DefaultMutableTreeNode carpeta = new DefaultMutableTreeNode(f);
                getNodos().add(new DesArbol(new File(ruta),f));
                modelo.insertNodeInto(carpeta, padre, i++);
            } else {
                String ruta = path + "/" + f;
                //System.out.println(ruta);
                File dir = new File(ruta);
                String archivos[] = dir.list();
                DefaultMutableTreeNode carpeta = new DefaultMutableTreeNode(f);
                modelo.insertNodeInto(carpeta, padre, i++);
                leerDirectorio(archivos, ruta,carpeta,0);
            }
        }
        return;
    }

    public DefaultMutableTreeNode getCarpetaRaiz() {
        return carpetaRaiz;
    }

    public DefaultTreeModel getModelo() {
        return modelo;
    }

    public ArrayList<DesArbol> getNodos() {
        return nodos;
    }
    
    public DesArbol getNodo(String nom){
        for(DesArbol d : getNodos()){
            if(d.getNombre().equals(nom))
                return d;
        }
        return null;
    }
    
    public boolean validarExistencia(String ruta){
        return ruta.split("\\.").length>0?true:false;
    }
    
    public String producirContenido(DesArbol a) {
        try {
            try {
                return this.getCode(a.getFile().getAbsolutePath());
            }catch (NullPointerException e){
            }
        } catch (IOException ex) {
        }
        return "n/a";
    }
    
    public String getCode(String ruta) throws IOException{
			InputStream in = new FileInputStream(ruta);
        if( in == null ){
                return "n/a";
        }
        if( in != null ){
                StringBuilder builder = new StringBuilder();
                InputStreamReader reader = new InputStreamReader( in, "UTF-8" );
                int next;
                while( (next = reader.read()) != -1 ){
                        if( next == '\t' ){
                                builder.append( "    " );
                        }
                        else{
                                builder.append( (char)next );
                        }
                }
                reader.close();
                return builder.toString();
        }
        return "n/a";
    }
    
}
