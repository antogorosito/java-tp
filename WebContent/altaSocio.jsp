<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Alta socio</title>
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
        	<a class="nav-link" href="index.html">Home</a>
        </li>
        </ul>
      
        
      </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">Alta de socio</h1>    
      </div>
    </div>
    
     <div class="container">
     
      			 
   	<form class="form-alta" action="altaSocio" method="post">
   	
   		<p><label>Nombre:</label>
   		<input name="nombre"type="text" id="nombre"></p>
   		
   		<p><label>Apellido:</label>
   		<input name="apellido"type="text" id="apellido"></p>
   		
   		<p><label>Email:</label>
   		<input name="email"type="email" id="email"></p>
   		
   			<p><label>Domicilio:</label>
   		<input name="domicilio"type="text" id="domicilio"></p>
   		
   			<p><label>Telefono:</label>
   		<input name="telefono"type="text" id="telefono"></p>
   		
   			<p><label>Dni:</label>
   		<input name="dni"type="text" id="dni"></p>
   		
   		 <button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px">Registrar</button>
  
   	</form>
  
    </div>

    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>