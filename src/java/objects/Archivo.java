/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.File;

/**
 *
 * @author Ruben35
 */
public class Archivo {
    private File file;
    private String name;

    public Archivo(File file, String name) {
        this.file = file;
        this.name = name;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
