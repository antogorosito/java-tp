<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Alta libro</title>
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
	        	<a class="nav-link" href="logout">Home</a>
        	</li>
        </ul>
	</div>
   <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    	<div class="container">
        	<h1 class="display-3">Alta de libro</h1>    
      	</div>
    </div>
    <div class="container">
    	<form class="form" action="altaLibro" method="post">
   			<p><label>Titulo:</label>
   			<input name="titulo"type="text" required="required"></p>		
   			<p><label>ISBN:</label>
   			<input name="ISBN"type="text" required="required"></p>   		
   			<p><label>Nro Edicion:</label>
   			<input name="nroEdicion"type="text" required="required" ></p>   		
   			<p><label>Fecha Edicion:</label>
   			<input name="fechaEdicion"type="text" required="required" placeholder="yyyy-MM-dd"></p>
   			<p><label>Cantidad dias maximo de prestamo:</label>
	   		<input name="cantDiasMaxPrestamo"type="text"></p>
   			<button class="btn btn-primary " style="margin-right: 50px" type="submit" name="op" value="Registrar">Registrar</button>
         	<button class="btn btn-info " style="margin-right: 50px" type="submit" name="op" value="Cancelar" formnovalidate>Cancelar</button>
         	<%String msj=(String)request.getAttribute("errorAltaL");
 			if (msj != null) {%>
 			<label style="color:red;"><%=msj %></label>
 			<%}%>
	   	</form>
    </div>
	<div class="container">
    	<footer>
        	<p>� Trabajo practico java -2018 - Gorosito, Velazquez</p>
      	</footer>
    </div> <!-- /container -->
</body>
</html>