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

    <title>Prestamo de libros</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/jumbotron.css" rel="stylesheet">
</head>
<body>   
	<div class="header clearfix">
    	<img src="imagenes/logo.jpg" title="logo" width="300" height="90"/>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    	<div class="container">
        	<h1 class="display-3">Prestamos de libros</h1>    
        </div>
    </div>

    <div class="container">
    <%Socio s=(Socio)session.getAttribute("socio");%>
    	<label>Apellido y nombre: <%=s.getApellido() +" "+ s.getNombre()%></label>
    	<form class="form-bus" action="agregar" method="post">
    		<label>Id ejemplar: </label>  
    		<input type="text" name="idEjemplar" required="required" >   
   			<button class="btn btn-primary " type="submit" style="margin-right: 50px" name="op" value="Agregar" >Agregar</button>
   			<button class="btn btn-info" style="margin-right: 50px" type="submit" name="op" value="Cancelar" formnovalidate>Volver</button>
    		<%String msj=(String)request.getAttribute("error");
	 		if (msj != null) {%>
 			<label style="color:red;"><%=msj %></label>
 			<%}%>
	
		    <%ArrayList<LineaDePrestamo> li= (ArrayList<LineaDePrestamo>)session.getAttribute("lineas");%>  
			<%if (li!=null){ %>
		 	<table class="table" >
 				<tr>
					<th>Id ejemplar</th>
 					<th>Titulo</th>
 					<th>Maximo dias</th>
		 		</tr>
		 		<%for(LineaDePrestamo ll: li){%>
		 		<tr>
 		 			<td><%=ll.getEjemplar().getIdEjemplar() %></td>
 		 			<td><%=ll.getEjemplar().getLibro().getTitulo() %></td>
 		 			<td><%=ll.getEjemplar().getLibro().getCantDiasMaxPrestamo() %></td>
	 			</tr>
 				<%} %>
 			</table>
 			<%int dias=(Integer)session.getAttribute("dias"); %>
 			<label>Cantidad de dias maximos de prestamo: </label>
 			<input type="text" name="diasMaximoPrestamo" value=<%=dias%>> 				
     		<button class="btn btn-primary " style="margin-right: 50px" type="submit" name="op" value="Guardar" formnovalidate>Guardar</button>
     		<button class="btn btn-info" style="margin-right: 50px" type="submit" name="op" value="Cancelar" formnovalidate>Cancelar</button>
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