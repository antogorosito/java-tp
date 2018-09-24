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
        
  
        
      </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">Devolución de libros</h1>    
      </div>
    </div>
     
      <div class="container">
      <form class="form-bus" action="devueltoVarios" method="post">
      	<label>Id ejemplar:</label>
      	<%if(request.getAttribute("id1")!=null) {%>
      	<input type="text" name="idEjemplar1" value=<%=request.getAttribute("id1") %>>
      	<%}else{ %>
      	<input type="text" name="idEjemplar1">
      	<%} %>
      	<label>Id ejemplar 2:</label>
      	<%if(request.getAttribute("id2")!=null) {%>
      	<input type="text" name="idEjemplar2" value=<%=request.getAttribute("id2") %>>
      	<%}else{ %>
      	<input type="text" name="idEjemplar2">
      	<%} %>	
      <label>Id ejemplar 3:</label>
      	<%if(request.getAttribute("id3")!=null) {%>
      	<input type="text" name="idEjemplar3" value=<%=request.getAttribute("id3") %>>
      	<%}else{ %>
      	<input type="text" name="idEjemplar3">
      	<%} %>
      	<label>Id ejemplar 4:</label>
      	<%if(request.getAttribute("id4")!=null) {%>
      	<input type="text" name="idEjemplar4" value=<%=request.getAttribute("id4") %>>
      	<%}else{ %>
      	<input type="text" name="idEjemplar4">
      	<%} %>
      	<label>Id ejemplar 5:</label>
      	<%if(request.getAttribute("id5")!=null) {%>
      	<input type="text" name="idEjemplar5" value=<%=request.getAttribute("id5") %>>
      	<%}else{ %>
      	<input type="text" name="idEjemplar5">
      	<%} %>
      	
      	 <%String msj=(String)request.getAttribute("error");
  
 		if (msj != null) {%>
 		<label style="color:red;"><%=msj %></label>
 		<%}%>
 		
 		 <%String msj2=(String)request.getAttribute("error2");
  
 		if (msj2 != null) {%>
 		<label style="color:red;"><%=msj2 %></label>
 		<%}%>
      	<button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px" name="opp" value="Agregar">Agregar</button>
      	<button class="btn btn-lg btn-primary " type="submit" style="margin-right: 50px" name="opp" value="Cancelar">Cancelar</button>
      	
      </form>
    
  
      </div>

    
      <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>