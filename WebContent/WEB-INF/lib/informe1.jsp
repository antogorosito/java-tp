<%@page import="negocio.CtrlLineaDePrestamo"%>
<%@page import="negocio.CtrlSocio"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="entidades.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Informe </title>
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
    <div class="jumbotron">
    	<div class="container">
	        <h1 class="display-3">Informe de los libros pendientes de devolucion</h1>    
      	</div>
    </div>
    <div class="container">
 		<%ArrayList<LineaDePrestamo> lineas=(ArrayList<LineaDePrestamo>)request.getAttribute("listaPendiente");
   		if(lineas.isEmpty()==false){%>
	    <table class="table">
		   	<tr>
				<th>Titulo</th>
				<th>ID ejemplar</th>
				<th>ID prestamo</th>
				<th>Fecha a devolver</th>
 				<th>Nombre y apellido</th>
	 		</tr>
 		<% 	for(LineaDePrestamo lp: lineas){%>
   			<tr>
   				<td><%=lp.getEjemplar().getLibro().getTitulo() %></td>
   				<td><%=lp.getEjemplar().getIdEjemplar() %></td>
   				<td><%=lp.getPrestamo().getIdPrestamo() %></td>
   				<td><%=lp.getPrestamo().getFechaADevolver() %></td>
 				<td><%=lp.getPrestamo().getSocio().getNombre() +" "+lp.getPrestamo().getSocio().getApellido() %></td> 	
  			</tr>   <%} %>
   		</table> 
	   	<%}  %>
	   <%String msj=(String)request.getAttribute("error");
	 		if (msj != null) {%>
 			<label style="color:red;"><%=msj %></label>
 			<%}%>
 			
		<form class="form-bus" action="informes" method="post">
			<button class="btn btn-info" style="margin-right: 50px" type="submit" name="op" value="Volver">Volver</button>
		</form>
	</div>
    <div class="container">
 	   <footer>
    	   <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
       </footer>
    </div> <!-- /container -->
</body>
</html>