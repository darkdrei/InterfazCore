/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author dark
 */
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

public class UtilJDialog {
	public static JDialog dameNuevoJDialog (Component padre)
	{
		JDialog dialogo;
    	// Obtenemos la ventana que contiene al botón que se ha
    	// pulsado.
    	// e.getSource() nos devuelve ese botón.
    	// SwingUtilities.getWindowAncestor() nos devuelve la ventana
    	// que contiene ese botón, es decir, en este caso el
    	// JFrame principal.
        Window ventanaPadre = SwingUtilities.getWindowAncestor(padre);

        // Codigo general para crear el JDialog en función del
        // tipo de ventana padre.
        if (ventanaPadre instanceof Frame)
        {
            dialogo = new JDialog((Frame) ventanaPadre);
        }
        else if (ventanaPadre instanceof Dialog)
        {
            dialogo = new JDialog((Dialog) ventanaPadre);
        }
        else
        {
            dialogo = new JDialog();
        }

        return dialogo;
	}
}