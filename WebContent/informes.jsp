%@page import="com.mysql.cj.Session"%>
<%@page import="entidades.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Informes</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/jumbotron.css" rel="stylesheet">
    
       
     
  </head>

  <body>   
     
      <div class="header clearfix">
        
        <img src="imagenes/logo.jpg" title="logo" width="300" height="90"/>
        
       <ul class="nav nav-pills float-right">
        <li class="nav item">
        <a class="nav-link" href="index.html">Cerrar sesion</a>
        </li>
        </ul>
          <ul class="nav nav-pills float-right">
        <li class="nav item">
        	<a class="nav-link" href="menu.jsp">Menu</a>
        </li>
        </ul>
        
        <ul class="nav nav-pills float-right">
        <li class="nav item">
        	<a class="nav-link" href="index.html">Home</a>
        </li>
        </ul>
      
        
      </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">Informes</h1>    
      </div>
    </div>
      <div class="container">
   <div class="row">
        <div class="col-md-4">
          <h2>Libros mas consultados</h2>
          <p>Imprimir informe sobre los libros mas buscados el ultimo mes. </p>
          <p><a class="btn  btn-info" href="informe1.jsp" role="button">Ir  </a></p>
        </div>
        <div class="col-md-4">
          <h2>Socios sancionados</h2>
          <p>Imprimir informe sobre todos los socios que se encuentran actualmente sancionados. </p>
          <p><a class="btn btn-info" href="informe2.jsp" role="button">Ir</a></p>
       </div>
    </div>
  
     </div>

    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>