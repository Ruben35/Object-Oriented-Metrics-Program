<%-- 
    Document   : muestraMetricas
    Created on : 22/02/2020, 07:41:39 PM
    Author     : Ruben35
--%>
<%@page import="objects.Metrica"%>
<%@page import="java.util.ArrayList"%>
<%HttpSession sesion = request.getSession();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/bulma.css" type="text/css"/>
        <link rel="stylesheet" href="./fonts/css/all.css"/> 
        <link rel="icon" type="image/png" href="./img/java.png" />
        <title>Metrics</title>
        <%
            ArrayList<Metrica> metricas=new ArrayList<Metrica>();
            if(sesion.getAttribute("metricas")==null){
                response.sendRedirect("./");
            }else{
                metricas=(ArrayList<Metrica>)sesion.getAttribute("metricas");
                sesion.removeAttribute("metricas");
            }
        %>
        <style>
            .hero.is-purple{
                
                border-color: #fff;
                background: rgb(71,4,89);
                background-image: linear-gradient(90deg, rgba(71,4,89,1) 0%, rgba(110,15,112,1) 35%, rgba(203,7,208,1) 100%);
            }
            .title.is-white{
                    color: #fff;
            }
            .is-purple{
                background-color: #6e0f70;
            }
            
        </style>
    </head>
    <body>
        <section class="hero is-purple">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title is-white">Metrics</h1>
                </div>
            </div>
        </section><br><br>
        <section class="container">
            <%
                int contadorDeClases=0;
                for(int i=0;i<metricas.size();i++){
                    if(contadorDeClases==0)
                        out.println("<div class=\"columns is-centered\">");
                    
                    String color=(i%2==0)?"is-dark":"is-light";
                    out.println("<div class='column'>"+
                        "<div class='notification "+color+"'>"+
                            "<div class='content has-text-centered '>"+
                                "<label class='has-text-weight-bold is-size-4 is-bold' >Clase "+metricas.get(i).getNameOfClass()+"</label>"+
                                "<div class='content'>"+
                                "<div>Weighted Methods Per Class</div>"+
                                "<label class='has-text-weight-semibold is-size-4'>"+metricas.get(i).getWMC()+"</label>"+
                                "<div>Profundidad del árbol de herencia</div>"+
                                "<label class='has-text-weight-semibold is-size-4'>"+metricas.get(i).getDIT()+"</label>"+
                                "<div>Número de hijos</div>"+
                                "<label class='has-text-weight-semibold is-size-4'>"+metricas.get(i).getNOC()+"</label>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</div>");
                    contadorDeClases++;
                    if(contadorDeClases==3){
                        contadorDeClases=0;
                        out.println("</div>");
                    }
                }
            %>
        </section>
    </body>
 
</html>
