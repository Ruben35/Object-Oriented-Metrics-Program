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
            ArrayList<Metrica> metricas= new ArrayList<Metrica>();
            if(sesion.getAttribute("metricas")==null){
                response.sendRedirect("./");
                System.out.println("paso");
            }else{
                metricas=(ArrayList<Metrica>)request.getAttribute("metricas");
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
            <div class="columns is-centered">
                <div>
                    <!-- TODO -->
                </div>
            </div>
        </section>
    </body>
 
</html>
