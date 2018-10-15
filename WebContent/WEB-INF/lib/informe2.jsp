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
    	    <h1 class="display-3">Informe de los socios sancionados</h1>    
      	</div>
    </div>
    <div class="container">
    	<%CtrlSocio cs=new CtrlSocio();
 		ArrayList<Socio> sociosI = cs.getAllInhabilitados();
 		if(sociosI.isEmpty()==false){ %>
   		<table class="table">
		   	<tr>
				<th>ID socio</th>
		 		<th>Nombre y apellido</th>
 				<th>Dni</th>
 				<th>Telefono</th>
		 		<th>Direccion</th>
	 			<th>Email </th>	
 			</tr>
 			<% 	for(Socio ss: sociosI){%>
   			<tr>
   				<td><%=ss.getIdSocio() %></td>
			 	<td><%=ss.getNombre() +" "+ss.getApellido() %></td>
 				<td><%=ss.getDni() %></td>
 				<td><%=ss.getTelefono() %></td>
 				<td><%=ss.getDomicilio() %></td>
 				<td><%=ss.getEmail() %></td>	
  			</tr>   <%} %>
		</table> 
   		<%} %>
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