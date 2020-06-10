/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.data;

import java.awt.Component;
import java.util.Vector;

/**
 *
 * @author dark
 */
public class GestionVector {
    private Vector<Object> base;

    public GestionVector(Vector<Object> base) {
        this.base = base;
    }

    public Vector<Object> getBase(){
        return base;
    }    
    
    
    public int[] getInteger(){
        int r[] = new int[base.size()];
        int i=0;
        for (Object s : base) {
            int s1 = (int)s;
            r[i] = s1;
            i++;
        }
        return r;
    }
    
    public String[] getString(){
        String r[] = new String[base.size()];
        int i=0;
        for (Object s : base) {
            String s1 = (String)s;
            r[i] = s1;
            i++;
        }
        return r;
    }
    
    public boolean[] getBoolean(){
        boolean r[] = new boolean[base.size()];
        int i=0;
        for (Object s : base) {
            boolean s1 = (boolean)s;
            r[i] = s1;
            i++;
        }
        return r;
    }
    
    public float[] getFloat(){
        float r[] = new float[base.size()];
        int i=0;
        for (Object s : base) {
            float s1 = (float)s;
            r[i] = s1;
            i++;
        }
        return r;
    }
    
    public int tipoComponente(){
        if (this.extisteContenido()){
            if(this.getBase().get(0) instanceof String){
                return 1;
            }else if(this.getBase().get(0) instanceof Integer){
                return 2;
            }else if(this.getBase().get(0) instanceof Boolean){
                return 3;
            }else if(this.getBase().get(0) instanceof Float){
                return 4;
            }else if(this.getBase().get(0) instanceof Double){
                return 5;
            }
        }
        return 0;
    }
    
    public boolean extisteContenido(){
        return this.base.size() > 0 ? true:false;
    }
}
