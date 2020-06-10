/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author dark
 */
public class ValidXml implements Validacion {

    @Override
    public boolean exisFile(String ruta) {
        File xml_file = new File(ruta);
        return xml_file.isFile();
    }

    @Override
    public boolean validExtencion(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean validExtencion(String f){
        String[] dir = f.split("\\.");
        if (dir.length <2)
            return false;
        return dir[1].equalsIgnoreCase("xml")?true:false;
    }

    @Override
    public boolean validEstructura(File xml_file) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = (Document)builder.build(xml_file);
            Element rootNode = document.getRootElement();
            List list = (List) rootNode.getChildren();
            Xml xml = new Xml();
            for (Object object : list) {
                Element ob = (Element) object;
                if (ob.getName().equalsIgnoreCase("autor")) {
                    try {
                        xml.getAutor().setNombre(ob.getChild("nombre").getText());
                    } catch (NullPointerException r) {
                        xml.getAutor().setNombre("");
                    }
                    try {
                        xml.getAutor().setDescripcion(ob.getChild("descripcion").getText());
                    } catch (NullPointerException r) {
                        xml.getAutor().setDescripcion("");
                    }
                    try {
                        xml.getAutor().setVersion(ob.getChild("version").getText());
                    } catch (NullPointerException r) {
                        xml.getAutor().setVersion("");
                    }
                } else if (ob.getName().equalsIgnoreCase("cuerpo")) {
                    try {
                        Element tipo = ob.getChild("tipo");
                        xml.getCuerpo().setColumnas(Integer.parseInt(tipo.getAttribute("columnas").getValue()));
                        xml.getCuerpo().setTipo_datos(tipo.getAttribute("tipodatocolumna").getValue().split(","));
                    } catch (NullPointerException r) {
                        xml.getCuerpo().setColumnas(0);
                        xml.getCuerpo().setTipo_datos(new String[]{});
                    }catch(NumberFormatException n){
                        xml.getCuerpo().setColumnas(0);
                        xml.getCuerpo().setTipo_datos(new String[]{});
                    }
                    try {
                        Element tipo = ob.getChild("claseprincipal");
                        xml.getCuerpo().setMain(ob.getChild("claseprincipal").getValue());
                    } catch (NullPointerException r) {
                        xml.getCuerpo().setMain("");
                    }
                    ArrayList<String> parametros = new ArrayList<>();
                    try {
                        Element param= ob.getChild("parametro");
                        for (Element e : param.getChildren()) {
                            try {
                                parametros.add(e.getName());
                            } catch (NullPointerException r) {
                                System.err.println("Se exploto");
                            }
                        }
                    } catch (NullPointerException r) {
                        
                    }finally{
                        xml.getCuerpo().setParametros(parametros);
                    }
                }

            }
            boolean respuesta = true;
            if(!xml.getAutor().getNombre().equals("")){
                return false;
            }else if(xml.getCuerpo().getTipo_datos().length == 0){
                return false;
            }else if(xml.getCuerpo().getParametros().size() == 0){
                return false;
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ValidXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidXml.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        }
        return true;
    }
    
}
