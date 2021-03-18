/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
/**
 * Estructura de codigo tomada de la pagina de documentacion y modificado para el uso en la tesis 
 * @author https://www.javatips.net/api/DockingFrames-master/docking-frames-common/src/bibliothek/gui/dock/common/DefaultSingleCDockable.java
 */


public class BaseSet {
	private Class<?>[] children;
	
	public BaseSet( Class<?>... children ){
		this.children = children;
	}
	
	public void append( Extension extension ){
		Class<?>[] tutorials = extension.getTutorials( getClass() );
		if( tutorials != null ){
			Class<?>[] temp = new Class[children.length + tutorials.length];
			children = temp;
		}
	}
	
	public Class<?>[] getChildren(){
		return children;
	}
}
