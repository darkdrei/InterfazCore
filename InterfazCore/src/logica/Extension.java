package logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Allows to load additional classes as tutorials.
 * @author Benjamin Sigg
 */
public interface Extension {
	/**
	 * Tells what additional classes are available for the {@link BaseSet} <code>set</code>.
	 * @param set the set into which to add additional tutorials
	 * @return the additional tutorials or {@link BaseSet}s
	 */
	public Class<?>[] getTutorials( Class<? extends BaseSet> set );
}

