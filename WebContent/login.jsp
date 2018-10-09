<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Login</title>
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
        	<a class="nav-link" href="index.html">Home</a>
        </li>
        </ul>
      
        
      </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">Inicio de sesion</h1>    
      </div>
    </div>
    
     <div class="container">
   <form class="form-signin" action="login" method="post">
      
    
      <label >Usuario</label>
      <input type="text"  name="usuario" class="form-control"  required="required"  style="margin-bottom: 15px">
      <label >Clave</label>
      <input type="password"  name="clave" class="form-control" required="required">
   
      <button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px">Iniciar sesion</button>
  
   <%String msj=(String)session.getAttribute("errorLogin");
  
 		if (msj != null) {%>
 		<label style="color:red;"><%=msj %></label>
 		<%}%>
    
    </form>
  
    </div>

    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>