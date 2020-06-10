/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;

/**
 *
 * @author dark
 * Encargada de proveer los mecanismo para la lectura y retorno de los archivos
 */
public interface Lector {
    /**
     * Se encarga de validar el tipo de archivo
     * @param f
     * @return 
     */
    public boolean validarArchivo(File f);
    
    /**
     *Se encarga de validar el archivo y agregar la respuesta del porque no es valido
     * @param f
     * @return
     */
    public Object[] validarArchivoGrup(File f);
    
    /**
     * Se encarga de validar el archivo, junto a la extencion del archivo. 
     * @param f
     * @param ext
     * @return 
     */
    public boolean validarArchivo(File f,String ext);
    
    /**
     * Se encarga de la descompresion de los archivos, por la referencia del archivo
     * @param f
     * @return 
     */
    public File[] getFiles(File f);
    
    /**
     * Se encarga de la descompresion por el nombre del archivo
     * @param f
     * @return 
     */
    public File[] getFiles(String f);
    
    public void getsFiles(File f);
    
    
}
