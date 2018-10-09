
<%@page import="entidades.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Alta ejemplar</title>
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
        <a class="nav-link" href="logout">Cerrar sesion</a>
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
        <h1 class="display-3">Alta de ejemplar</h1>    
      </div>
    </div>
    
     <div class="container">
           			 
   	<form class="form" action="altaEjemplar" method="post">
   	<% Libro l= (Libro)session.getAttribute("Libro");
    		if(l==null){%>
   		<p><label>ISBN:</label>
   		<input name="ISBN"type="text" required="required"></p>
   		   <% }else {%>
   		  <p><label>ISBN:</label>
   			<input name="ISBN"type="text" required="required" value=<%=l.getIsbn()%>></p>
   		 <% }%>
   		 
   		 
   		 <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="Buscar">Buscar</button>
   	<%Libro li=(Libro)session.getAttribute("L");
   			if(li!=null) {%>
   			<p><label>ID Ejemplar:</label>
   		<input name="idEjemplar"type="text" required="required"></p>
   		<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="Guardar">Guardar</button>
   		<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="Agregar mas">Agregar mas</button>
   		<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="Cancelar" formnovalidate>Cancelar</button>
   		<%} %>
   	</form>
  
    </div>

    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>