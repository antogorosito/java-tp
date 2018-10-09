<%@page import="entidades.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
    	<link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">
    	<title>Menu</title>
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
	        		<a class="nav-link" href="index.html">Home</a>
        		</li>
        	</ul>     
		</div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
      		<div class="container">
        		<h1 class="display-3">Menu biblioteca</h1>    
      		</div>
    	</div>
    <%Usuario u =(Usuario)session.getAttribute("usuario");
    		if(u.getTipo()==0){%>
     	<div class="container">
			<form class="form-bus" action="menu" method="post">
   				<div class="row">
        			<div class="col-md-4">
          				<h2>Alta socio</h2>
          				<p>Realizar el registros de nuevos socios a la biblioteca. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="altaSocio">Ir</button>
        			</div>
        			<div class="col-md-4">
	          			<h2>Alta libro</h2>
          				<p>Realizar el registros de nuevos libros y ejemplares en la biblioteca. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="altaLibro">Ir</button>
        			</div>
        		   	<div class="col-md-4">
          				<h2>Alta Ejemplar</h2>
          				<p>Realizar el registro de nuevos ejemplares de libros en la biblioteca. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="altaEjemplar">Ir</button>
       				</div>    
      			</div>
      		
				<div class="row">
					<div class="col-md-4">
          				<h2>Consultas</h2>
          				<p>Realizar consultas sobre la disponibilidad de los libros. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="consulta">Ir</button>
       				</div>    
       				<div class="col-md-4">
          				<h2>Registrar prestamos</h2>
          				<p>Realizar el registro de prestamos de libros a domicilio a los socios.</p>
				       <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="prestamo">Ir</button>
        			</div>
        			<div class="col-md-4">
          				<h2>Registrar devoluciones</h2>
          				<p>Realizar el registro de la devolucion de los libros prestados a domicilio a los socios. Ingresando ID EJEMPLAR</p>
          			 <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="devueltoUno">Ir</button>
        			</div>
         		
       			</div> 
   
   				<div class="row">
   					<div class="col-md-4">
          				<h2>Registrar devoluciones </h2>
          				<p>Realizar el registro de la devolucion de los libros prestados a domicilio a los socios. Ingresando ID SOCIO</p>
          				 <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="devolver">Ir</button>
        			</div>
      				<div class="col-md-4">
          				<h2>Estado </h2>
          				<p>Realizar la modificacion del estado de los socios. </p>
          				 <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="estado">Ir</button>
       				</div>
         			<div class="col-md-4">
          				<h2>Informes</h2>
          				<p>Imprimir los respectivos informes. </p>
          				 <button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="informes">Ir</button>
        			</div>
      			</div>
			</form> 	
		</div>
<%} else if(u.getTipo()==1){ %>
		<div class="container">
			<form class="form-bus" action="menu" method="post">
				<div class="row">
          			<div class="col-md-4">
          				<h2>Prestamos pendientes</h2>
          				<p>Realizar consulta de los prestamos pendientes de devolucion. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="pendientes">Ir</button>
       				</div>
        			<div class="col-md-4">
          				<h2>Consultas</h2>
          				<p>Realizar consultas sobre la disponibilidad de los libros. </p>
          				<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="consultas">Ir</button>
       				</div>
        
     			 </div>
			</form>
		 </div>
<%} %>
    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>