/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import objects.Archivo;

/**
 *
 * @author Ruben35
 */
public class MedidorProfundidad {
    private static ArrayList<String> depth;
    
    /**
     * PRECONDITION: THE JAVA FILE MUST BE INDENTED
     * 
     * 
     * @param fileToAnalize File to analize Depth
     * @param filesToCompare All the classes
     * @param first First time you use this method
     * @return 
     */
    public static ArrayList<String> getDepthTree(Archivo fileToAnalize, ArrayList<Archivo> filesToCompare,boolean first){
        if(first){
            depth=new ArrayList<>();
        }
        if(fileToAnalize!=null){
            if(itHasExtendsOrImplements(fileToAnalize)){
                Archivo file=isOnFiles(depth.get(depth.size()-1),filesToCompare);
                return getDepthTree(file,filesToCompare,false);
            }else{
                return getDepthTree(null,filesToCompare,false);
            }
        }else{
            return depth;
        }               
    }

    private static boolean itHasExtendsOrImplements(Archivo fileToAnalize) {
        FileReader fileR = null;
        BufferedReader buffer = null;
        boolean itHas=false;
        
        try{
            fileR =new FileReader(fileToAnalize.getFile());
            buffer=new BufferedReader(fileR);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            String lines = "";
            boolean stop= true;
            boolean alreadySpecialWord=false;
            boolean next=false;
            while( (( lines = buffer.readLine()) != null) && stop) {
                if(lines!=null){
                    if(lines.contains("extends") || alreadySpecialWord){
                        if(!lines.equals("\n")){
                            alreadySpecialWord=true; //If the word is in other line but already we pass at least one extends or implements
                            String[] words=lines.split(" ");
                            String isClass="";
                            itHas=true;
                            for(int i=0;i<words.length;i++){
                                if(next){
                                    isClass=words[i].replace("{", "");
                                    next=false;
                                    stop=false;
                                }
                                if(words[i].contains("extends")){
                                    next=true;
                                }
                            }
                                depth.add(isClass);
                        }
                    }
                }else{
                    stop=false;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return itHas;
    }
    
    private static Archivo isOnFiles(String nameOfClass, ArrayList<Archivo> filesToCompare){
        String nameOfArchivo=nameOfClass+".java";
        for(int i=0;i<filesToCompare.size();i++){
            if(nameOfArchivo.equals(filesToCompare.get(i).getName())){
                return filesToCompare.get(i);
            }
        }
        
        return null;
    }
}
