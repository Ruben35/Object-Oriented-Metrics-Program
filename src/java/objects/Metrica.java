/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import classes.MedidorClaseHija;
import java.util.ArrayList;

/**
 *
 * @author Ruben35
 */
public class Metrica{
    //temporales tipo de dato (lo más seguro es que sea int todos)
    private int WMC;// Complejidad
    private ArrayList<String> DIT;// Profundidad
    private int NOC;// Hijos
    private String nameOfClass;
    private ArrayList<String> methodsAndComplexity;
    
    //Aqui van los set de cada metrica que se llenaran con los metodos estaticos de cada clase del paquete "classes"

    public void setWMC(int WMC) {
        this.WMC = WMC;
    }

    public void setDIT(ArrayList<String> DIT) {
        this.DIT = DIT;
    }

    public void setNOC(int NOC) {
        this.NOC = NOC;
    }
    
    public void setNameOfClass(String nameOfClass){
        this.nameOfClass= nameOfClass.replace(".java", "");
    }

    public void setMethodsAndComplexity(ArrayList<String> methodsAndComplexity) {
        this.methodsAndComplexity = methodsAndComplexity;
    }
    
    //Getters
    
    public String getNameOfClass() {
        return nameOfClass;
    }    
    
    public int getWMC() {
        return WMC;
    }

    public ArrayList<String> getDIT() {
        return DIT;
    }

    public int getNOC() {
        return NOC;
    }

    public ArrayList<String> getMethodsAndComplexity() {
        return methodsAndComplexity;
    }
    
}
