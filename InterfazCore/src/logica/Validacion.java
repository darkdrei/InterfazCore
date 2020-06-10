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
public interface Validacion {
    public boolean exisFile(String ruta);
    public boolean validExtencion(File f);
    public boolean validExtencion(String f);
    public boolean validEstructura(File xml);
}
