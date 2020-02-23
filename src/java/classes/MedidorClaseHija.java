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
        String nameToSearch=nameOfClass.replace(".java", "");
        for(int i=0;i<filesToAnalize.size();i++){
            String nameToAnalize=filesToAnalize.get(i).getName().replace(".java", "");
            if(!nameToSearch.equals(nameToAnalize)){
                if(isChild(nameToSearch,filesToAnalize.get(i).getFile()))
                    numberOfChilds++;
            }
        }
        
        return numberOfChilds;
    }
    
    public static boolean isChild(String nameOfClass,File source){
        FileReader fileR = null;
        BufferedReader buffer = null;
        boolean child=false;
        
        try{
            fileR =new FileReader(source);
            buffer=new BufferedReader(fileR);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            String lines = "";
            boolean stop= true;
            boolean alreadySpecialWord=false;
            while( (( lines = buffer.readLine()) != null) && stop) {
                if(lines!=null){
                    if(lines.contains("extends") || lines.contains("implements") || alreadySpecialWord){
                        alreadySpecialWord=true; //If the word is in other line but already we pass at least one extends or implements
                        if(lines.contains("{"))//To stop searching in lines
                            stop=false;
                        String[] words=lines.split(" ");
                        for(int i=0;i<words.length;i++){ //Search on each word
                             if(words[i].contains("{")) // case "extends NameClass{"
                                 words[i]=words[i].replace("{", "");
                             if(words[i].trim().equals(nameOfClass))
                                child=true;
                        }
                    }
                }else{
                    stop=false;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return child;
    }
}
