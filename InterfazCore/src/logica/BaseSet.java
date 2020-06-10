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



public class BaseSet {
	private Class<?>[] children;
	
	public BaseSet( Class<?>... children ){
		this.children = children;
	}
	
	public void append( Extension extension ){
		Class<?>[] tutorials = extension.getTutorials( getClass() );
		if( tutorials != null ){
			Class<?>[] temp = new Class[children.length + tutorials.length];
			System.arraycopy( children, 0, temp, 0, children.length );
			System.arraycopy( tutorials, 0, temp, children.length, tutorials.length );
			children = temp;
		}
	}
	
	public Class<?>[] getChildren(){
		return children;
	}
}
