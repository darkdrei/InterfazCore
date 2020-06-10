/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;

/**
 *
 * @author dark
 */
public interface OperacionFIle {
    public File loadFile(String ruta);
    public void loadingFile(String ruta);
    public void readNodeFile(File f);
    public void readNodeFile();
    public void extraerInformacion();
    public void writeFile();
    public void writeFile(String ruta);
    public void writeFile(String ruta, Xml xml);
    public void readFile(Xml xml);
}
