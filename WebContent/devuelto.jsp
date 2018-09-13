<%@page import="com.mysql.cj.Session"%>
<%@page import="entidades.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Devolución de libros</title>
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
        	<a class="nav-link" href="menu.jsp">Menu</a>
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
        <h1 class="display-3">Devolución de libros</h1>    
      </div>
    </div>
 
      <div class="container">
  
    
   <%LineaDePrestamo li= (LineaDePrestamo)session.getAttribute("lineaPre");
       if(li!=null){
    	   Socio s=(Socio)request.getAttribute("socio");
       String socio= s.getApellido() +" "+ s.getNombre();%>
       
        <table class="table" >
 		<tr>
			<th>Id ejemplar</th>
			<th>Titulo</th>
			<th>Socio</th>
 		</tr>
 		
 		<tr>
 		 <td><%=li.getEjemplar().getIdEjemplar()%></td>
 		 <td><%=li.getEjemplar().getLibro().getTitulo()%></td>
 		 <td><%=socio%></td>	 
 		</tr>
 		
 		</table>
 			<form class="form-signin" action="devuelto" method="post">
 		
 	
 		 <button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px" name="opci" value="Registrar">Registrar</button>
 		 <button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px" name="opci" value="Volver">Volver atras</button>
 		 </form>
		<%} %>
		
      </div>

    
      <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>