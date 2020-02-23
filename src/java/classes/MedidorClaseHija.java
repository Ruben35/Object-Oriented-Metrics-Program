/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import objects.Archivo;

/**
 *
 * @author Ruben35
 */
public class MedidorClaseHija {
    
    public static int getChildsOnClass(String nameOfClass, ArrayList<Archivo> filesToAnalize){
        int numberOfChilds=0;
        for(int i=0;i<filesToAnalize.size();i++){
            if(!filesToAnalize.get(i).getName().equals(nameOfClass)){
                if(isChild(nameOfClass,filesToAnalize.get(i).getFile()))
                    numberOfChilds++;
            }
        }
        
        return numberOfChilds;
    }
    
    private static boolean isChild(String nameOfClass,File source){
        FileReader fileR = null;
        BufferedReader buffer = null;
        try{
            fileR =new FileReader(source);
            buffer=new BufferedReader(fileR);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        try{
            String lines = "";
            while( ( lines = buffer.readLine()) != null) {
                System.out.println(lines);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return true;
    }
}
