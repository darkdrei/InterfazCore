/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class ComponenXml implements OperacionFIle, XmlInterface {

    private SAXBuilder builder;
    private File file;
    private Document document;
    private Element rootNode;
    private Xml xml;

    public ComponenXml() {
        this.builder = new SAXBuilder();
        this.file = null;
        this.document = null;
        this.rootNode = null;
        xml = new Xml();
    }

    public ComponenXml(File f) {
        this.builder = new SAXBuilder();
        this.file = f;
        this.document = null;
        this.rootNode = null;
        xml = new Xml();
    }

    @Override
    public File loadFile(String ruta) {
        return (new File(ruta));
    }

    @Override
    public void loadingFile(String ruta) {
        this.setFile(loadFile(ruta));
        if (this.file.exists()) {
            try {
                setDocument(this.getBuilder().build(this.file));
            } catch (JDOMException ex) {
                Logger.getLogger(ComponenXml.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ComponenXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SAXBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(SAXBuilder builder) {
        this.builder = builder;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Element getRootNode() {
        return rootNode;
    }

    public void setRootNode(Element rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void readNodeFile(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Xml getXml() {
        return xml;
    }

    public void setXml(Xml xml) {
        this.xml = xml;
    }

    @Override
    public void readNodeFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void extraerInformacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile(String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile(String ruta, Xml xml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readFile(Xml xml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeXml(Xml xml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Xml getXmlById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean xmlExist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return xml.toString();
    }

    @Override
    public void removeXml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFile(ArrayList<Xml> xmls) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPositionXmlById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFile(int position, Xml xml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
