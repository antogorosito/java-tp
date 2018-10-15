<%@page import="com.mysql.cj.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">
    <title>Consultas</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/jumbotron.css" rel="stylesheet"> 
</head>
<body>   
	<div class="header clearfix">
    	<img src="imagenes/logo.jpg" title="logo" width="300" height="90"/>
        <%Usuario u=(Usuario)session.getAttribute("usuario");
    	if (u!=null){%>
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
        <%}%>
	</div>
    <div class="jumbotron">
	    <div class="container">
    	    <h1 class="display-3">Consulta de libros</h1>    
      	</div>
    </div>
    <div class="container">
   		<form class="consulta" action="consultas" method="post">
			<label>Titulo del libro:</label>
			<% String titulo= (String)request.getParameter("titulo"); 	
			if(titulo==null){%>
      		<input type="text" name="titulo" required="required" >
      		<%} else { %>
        	<input type="text" name="titulo" required="required" value=<%=titulo %>><%} %>
    		<button class="btn btn-primary " type="submit" style="margin-right: 50px" name="op" value="Buscar">Buscar</button>  
    		<button class="btn btn-info " type="submit" style="margin-right: 50px" name="op" value="Volver" formnovalidate>Volver</button>
    	</form>    
  		<%String msj=(String)request.getAttribute("errorCon");
  	 	if (msj != null) {%>
 		<label style="color:red;"><%=msj %></label>
 		<%}%>
		<% ArrayList<Ejemplar> ejemplares=(ArrayList<Ejemplar>)request.getAttribute("listaejemplares");%>
		<%if (ejemplares!=null){ %>
 		<table class="table" >
 			<tr>
 				<th>Id ejemplar</th>
 				<th>Titulo</th>
 				<th>Maximo dias</th>
 				<th>ISBN</th>
 				<th>Nro edicion</th>
 				<th>Fecha edicion</th> 
 			</tr>
 			<% for(Ejemplar ee: ejemplares){%>
 			<tr>
			 	<td><%=ee.getIdEjemplar() %></td>
 				<td><%=ee.getLibro().getTitulo() %></td>
 				<td><%=ee.getLibro().getCantDiasMaxPrestamo() %></td>
 				<td><%=ee.getLibro().getIsbn() %></td>
 				<td><%=ee.getLibro().getNroEdicion() %></td>
 				<td><%=ee.getLibro().getFechaEdicion() %></td>
 			</tr>
 			<%} %>
 		</table>	 			 
  		<%}%>  
    </div>
    <div class="container">
   		<footer>
        	<p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      	</footer>
    </div> <!-- /container -->
</body>
</html>