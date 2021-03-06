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
   	<title>Estado de socios</title>
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
        	<h1 class="display-3">Estado de socios</h1>    
        </div>
	</div>

    <div class="container">
    	<form class="form-bus" action="estados" method="post">
    	<%String msj=(String)request.getAttribute("error");
	 		if (msj != null) {%>
 			<label style="color:red;"><%=msj %></label>
 			<%}%>
 			
    	<%ArrayList<Socio> sociosInhabilitar = (ArrayList<Socio>)session.getAttribute("sociosInhabilitar");
 		if(sociosInhabilitar.isEmpty() !=true){%>
		
			<label><b> SOCIOS A INHABILITAR:</b></label>
   			<table class="table">
   				<tr>
					<th>ID socio</th>
 					<th>Nombre y apellido</th>
 				</tr>
			 <%	for(Socio s:sociosInhabilitar){%>
   				<tr>
   					<td><%=s.getIdSocio() %></td>
 					<td><%=s.getNombre() +" "+s.getApellido() %></td>
  				</tr> 
    		<%} %>
  		   </table>
   			<button class="btn btn-primary " style="margin-right: 50px" type="submit" name="op" value="Inhabilitar">Inhabilitar</button>
   			
   	 		<%} else{%>
   	 		<label style="color:red;"><b> No hay socios para inhabilitar</b></label>
   	 		<%} %>
		</form>
	</div>
    <div class="container">
    	<form class="form-bus" action="estados" method="post">
    	<% 	ArrayList<Socio> sociosHabilitar =(ArrayList<Socio>)session.getAttribute("sociosHabilitar");
 		if(sociosHabilitar.isEmpty() !=true){%>
   		
     		<label><b> SOCIOS A HABILITAR:</b></label>
   			<table class="table">
   				<tr>
					<th>ID socio</th>
 					<th>Nombre y apellido</th>
 				</tr>
		 <%	for(Socio ss:sociosHabilitar){%>
   				<tr>
   					<td><%=ss.getIdSocio() %></td>
			 		<td><%=ss.getNombre() +" "+ss.getApellido() %></td>
  				</tr> 
    	<%} %>
  		   </table>
   			<button class="btn btn-primary " style="margin-right: 50px" type="submit" name="op" value="Habilitar">Habilitar</button>
   			
   	 	<%} else {%>
   	 	   <label style="color:red;"><b> No hay socios para habilitar</b></label>
   	 	   <%} %>
   	 		<p><button class="btn btn-info " style="margin-top: 50px" type="submit" name="op" value="Volver">Volver</button></p>	
		</form>
	</div>
    <div class="container">
		<footer>
	        <p>� Trabajo practico java -2018 - Gorosito, Velazquez</p>
		</footer>
    </div> <!-- /container -->

</body>
</html>