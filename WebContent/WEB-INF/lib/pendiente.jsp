<%@page import="entidades.*" %>
<%@page import="negocio.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Libros pendientes de devolución</title>
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
	        <h1 class="display-3">Libros pendientes de devolución</h1>    
      	</div>
    </div>
    <div class="container">   			 
    
    
   		<%Usuario u=(Usuario)session.getAttribute("usuario");%>
    	<p><label>Nombre y apellido:<%=u.getSocio().getApellido() +" "+ u.getSocio().getNombre()%></label></p>
    	<p><label>Estado: </label>  <%if(u.getSocio().getEstado()==false){ %> <label>Inhabilitado</label><%}else{ %><label>Habilitado</label><%} %></p>
    		<%String msj=(String)request.getAttribute("error");
	 	if (msj != null) {%>
 		<label style="color:red;"><%=msj %></label>
 		<%}%>  	
    	<%ArrayList<LineaDePrestamo>lineas=(ArrayList<LineaDePrestamo>)request.getAttribute("lineas");
    	if(lineas!=null){
    	if(lineas.isEmpty()!=true){%>
    	<table>
    		<tr>
				<th>Id ejemplar</th>
 				<th>Titulo</th>
 				<th>Fecha a devolver</th>
		 	</tr>
		 	<%for(LineaDePrestamo ll: lineas){%>
		 	<tr>
 		 		<td><%=ll.getEjemplar().getIdEjemplar() %></td>
 		 		<td><%=ll.getEjemplar().getLibro().getTitulo() %></td>
 		 		<td><%=ll.getPrestamo().getFechaADevolver() %></td>
	 		</tr>
 			<%} %>
    	</table>
    	<%}}%>
    	<form class="form-bus" action="pendientes" method="post">
			<button class="btn btn-info" style="margin-right: 50px" type="submit" name="op" value="Volver">Volver</button>
		</form>
    </div>
    <div class="container">
	    <footer>
    	    <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      	</footer>
    </div> 
</body>	
</html>