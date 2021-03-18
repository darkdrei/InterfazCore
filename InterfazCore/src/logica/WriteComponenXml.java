/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import logica.Xml.Parametro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author dark
 */
public class WriteComponenXml extends ComponenXml {

    public WriteComponenXml() {
        super();
    }

    @Override
    public void writeFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile(String ruta, Xml xml) {
        FileWriter writer = null;
        try {
            ValidXml vxml = new ValidXml();
            if (vxml.exisFile(ruta) && vxml.validExtencion(ruta)) {
                loadingFile(ruta);
                try {
                    FileInputStream fis = new FileInputStream(this.getFile());
                    setDocument(this.getBuilder().build(fis));
                    this.setRootNode(getDocument().detachRootElement());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WriteComponenXml.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDOMException ex) {
                    Logger.getLogger(WriteComponenXml.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WriteComponenXml.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String basePath = new File("").getAbsolutePath();
                ruta = basePath + "/src/configuracion/xml_configuracion.xml";
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    ruta = basePath + "\\src\\configuracion\\xml_configuracion.xml";
                }
                Element root = new Element("listado");
                Document doc = new Document();
                doc.setRootElement(root);
                XMLOutputter outter = new XMLOutputter();
                outter.setFormat(Format.getPrettyFormat());
                this.setFile(new File(ruta));
                outter.output(doc, new FileWriter(this.getFile()));
                FileInputStream fis = new FileInputStream(this.getFile());
                setDocument(this.getBuilder().build(this.getFile()));
                this.setRootNode(getDocument().detachRootElement());
                this.setDocument(new Document());
                this.setRootNode(new Element("listado"));
            }
            Element autor = new Element("autor");
            autor.addContent(new Element("nombre").setText(xml.getAutor().getNombre()));
            autor.addContent(new Element("descripcion").setText(xml.getAutor().getDescripcion()));
            autor.addContent(new Element("version").setText(xml.getAutor().getVersion()));
            Element cuerpo = new Element("cuerpo");
            Element tipo = new Element("tipo");
            Element status = new Element("status");
            tipo.setAttribute("columnas", "" + xml.getCuerpo().getColumnas());
            tipo.setAttribute("tipodatocolumna", String.join(",", xml.getCuerpo().getTipo_datos()));
            cuerpo.addContent(tipo);
            tipo.addContent(new Element("claseprincipal").setText(xml.getCuerpo().getMain()));
            status.setAttribute("active", String.valueOf(xml.getStatus().getActive()));
            Element parametro = new Element("parametro");
            for (Parametro dato : xml.getCuerpo().getParametros()) {
                parametro.addContent(new Element(dato.getTipo()).setAttribute("nombre", dato.getNombre()));
            }
            cuerpo.addContent(parametro);
            Element ruta_jar = new Element("ruta");
            ruta_jar.addContent(new Element("nombre").setText(xml.getRuta().getNombre()));
            ruta_jar.addContent(new Element("direccionXml").setText(xml.getRuta().getDireccionXml()));
            ruta_jar.addContent(new Element("direccionJar").setText(xml.getRuta().getDireccionJar()));
            Element pluguin = new Element("pluguin");
            pluguin.addContent(autor);
            pluguin.addContent(cuerpo);
            pluguin.addContent(status);
            pluguin.addContent(ruta_jar);
            this.getRootNode().addContent(pluguin);
            this.getDocument().setContent(this.getRootNode());
            writer = new FileWriter(ruta);
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(this.getDocument(), writer);
            outputter.output(this.getDocument(), System.out);
            writer.close(); // close writer
        } catch (IOException ex) {
            Logger.getLogger(WriteComponenXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(WriteComponenXml.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

}
