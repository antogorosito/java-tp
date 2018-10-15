
<%@page import="entidades.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Mensaje</title>
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
	</div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
    	<div class="container">
      		<%int nro=(Integer)session.getAttribute("opc");
    		if(nro==1){%>
        	<h1 class="display-3">Se ha registrado el alta del socio y usuario con exito</h1>
        	<%} else if(nro==2){%>
            <h1 class="display-3">Se ha registrado el alta del ejemplar con exito</h1>
        	<%} else if(nro==3){%>
         	<h1 class="display-3">Se ha registrado el prestamo con exito con el minimo de dias </h1>
         	<%}  else if(nro==4){%>
         	<h1 class="display-3">Se ha registrado el prestamo con exito </h1>
         	<%}  else if(nro==5){%>
         	<h1 class="display-3">Se ha registrado la devolucion con exito </h1>
         	<%}  else if(nro==6){%>
         	<h1 class="display-3">Se ha registrado la devolucion con exito y se ha sancionado al socio </h1>
         	<%} else if(nro==7){%>
         	<h1 class="display-3">Se ha registrado la inhabilitacion de los socios con exito </h1>
         	<%} else if(nro==8){%>
         	<h1 class="display-3">Se ha registrado la habilitacion de los socios con exito </h1>
         	<%}  %>
		</div>
	</div>
    <div class="container">
   		<form class="form-bus" action="mensaje" method="post">		 
   			<button class="btn btn-info" type="submit" style="margin-right: 50px">Volver</button>
   		</form>  
    </div>
    <div class="container">
    	<footer>
        	<p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      	</footer>
    </div> <!-- /container -->
</body>
</html>