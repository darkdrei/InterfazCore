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

import javax.swing.ImageIcon;

import bibliothek.gui.dock.common.action.CButton;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class CopyCodeAction extends CButton {

    private CodePanel panel;

    public CopyCodeAction(CodePanel panel) {
        
    }

    @Override
    protected void action() {
        panel.copy();
    }
}
