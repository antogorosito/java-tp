<%@page import="negocio.CtrlSocio"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="entidades.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
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
        
        <ul class="nav nav-pills float-right">
        <li class="nav item">
        	<a class="nav-link" href="index.html">Home</a>
        </li>
        </ul>
        
      </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">Informe de los libros mas consultados</h1>    
      </div>
    </div>

    <div class="container">
    
   
   <table class="table">
   	<tr>
		<th>ID socio</th>
 		<th>Nombre y apellido</th>
 		<th>Seleccionar</th>
 	</tr>
 	<%CtrlSocio cs=new CtrlSocio();
 	ArrayList<Socio> sociosInhabilitar = cs.getAllAInhabilitar();
 	if(sociosInhabilitar!=null){
 	for(Socio s:sociosInhabilitar){%>
   <tr>
   	<td><%=s.getIdSocio() %></td>
 	<td><%=s.getNombre() +" "+s.getApellido() %></td>
 	<td><input type="checkbox" name="chk" checked="checked" value=<%=s.getIdSocio() %>></td>
  </tr>   <%} }%>
  

   </table>
   <form class="form-bus" action="estado" method="post">
  	<button class="btn btn-lg btn-primary " style="margin-right: 50px" type="submit" name="op" value="Inhabilitar">Inhabilitar</button>
  </form>
   
    </div>


    <div class="container">
   
      <footer>
        <p>© Trabajo practico java -2018 - Gorosito, Velazquez</p>
      </footer>
    </div> <!-- /container -->



</body></html>