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
import tutorial.toolbar.common.CommonToolbarTutorials;
import tutorial.toolbar.core.CoreToolbarTutorials;

public class ExtencionBarra implements Extension{

	@Override
	public Class<?>[] getTutorials( Class<? extends BaseSet> set ){
		if( set.isAssignableFrom( CoreSet.class )){
			return new Class[]{ CoreToolbarTutorials.class };
		}
		if( set.isAssignableFrom( CommonSet.class )){
			return new Class[]{ CommonToolbarTutorials.class };
		}
		return null;
	}
	
}
