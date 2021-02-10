/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author balzac
 */
public interface XmlInterface {

    public File loadFile(String ruta);

    public void loadingFile(String ruta);

    public void readNodeFile(File f);

    public void readNodeFile();

    public void extraerInformacion();

    public void writeFile();

    public void writeFile(String ruta);

    public void writeFile(String ruta, Xml xml);

    public void readFile(Xml xml);

    public void removeXml(Xml xml);

    public void removeXml();

    public void updateFile(ArrayList<Xml> xmls);

    public void updateFile(int position, Xml xml);

    public void updateFile();

    public Xml getXmlById(int id);

    public int getPositionXmlById(int id);

    public boolean xmlExist(int id);
}
