/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.data;

import java.util.ArrayList;

/**
 *
 * @author dark
 */
public interface DAO{
    public void insertObject(Object o);
    public ArrayList<Object> listObjects();
    public Object[][] matrizObjects();
    public boolean validObject(Object o);
    public void updateObject(int id, Object o);
    public void eraseObject(int id);
}
