/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.MedidorClaseHija;
import classes.MedidorProfundidad;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Archivo;
import objects.Metrica;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ruben35
 */
public class generarMetricas extends HttpServlet {
    private boolean isMultipart;

    //TODO: Validar que son archivos Java y modificar Vista
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter( );
        
        if( !isMultipart ) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No se subio el archivo</p>"); 
            out.println("</body>");
            out.println("</html>");
            
        }else{
            ArrayList<Archivo> archivos=getArchivos(request); //ArrayList de Archivos subidos
            ArrayList<Metrica> metricas= new ArrayList<>();
            for(int i=0;i<archivos.size();i++){
                Metrica temp=new Metrica();
                temp.setWMC(1); //Aqui en lugar de 1 va el metodo estatico de MedidorComplejidad que haga la operacion.
                temp.setDIT(MedidorProfundidad.getDepthTree(archivos.get(i), archivos,true)); //Aqui en lugar de 1 va el metodo estatico de MedidorProfundidad que haga la operacion.
                temp.setNOC(MedidorClaseHija.getChildsOnClass(archivos.get(i).getName(), archivos));
                temp.setMethodsAndComplexity(new ArrayList<>()); //Aqui en lugar de arraylist va el metodo estatico de MedidorProfundidad
                temp.setNameOfClass(archivos.get(i).getName());             //Que me de strings "metodoUno() | WM: 3"
                metricas.add(temp);
                
            }
            
            HttpSession sesion= request.getSession();
            sesion.setAttribute("metricas",metricas);
            response.sendRedirect("./muestraMetricas");
        }
        
    }
    
    private ArrayList<Archivo> getArchivos(HttpServletRequest request) throws IOException {
        String filePath;
        int maxFileSize = 1 * 1024 *1024;
        int maxMemSize = 4 * 1024;
        File file ;
        ArrayList<Archivo> archivos = new ArrayList<>();
        
        // Check that we have a file upload request
        filePath = request.getRealPath("/temp"); 
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
   
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
   
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try { 
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            
            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fileName = fi.getName();     

                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write(file);
                    Archivo temp = new Archivo(file,fileName);
                    archivos.add(temp);
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return archivos;
    }
    
}
