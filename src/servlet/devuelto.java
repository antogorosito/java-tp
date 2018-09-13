package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class devuelto
 */
@WebServlet({ "/devuelto", "/Devuelto" })
public class devuelto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devuelto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("opci");
		if(op.equals("Registrar"))
		{
			HttpSession session = request.getSession();
		
			LineaDePrestamo l=(LineaDePrestamo)session.getAttribute("lineaPre");
			CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
			//verificar lo de la fecha si esta ok cambio solo la linea 
			// sino cambiar tmb estado del socio
			
			
			 java.util.Date fecha = new  java.util.Date();
			 DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
			 String fechaActu=Formato.format(fecha);
		 	String fechadev=Formato.format(l.getPrestamo().getFechaADevolver());
			
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		     try {
				java.util.Date date1 = sdf.parse(fechaActu);
				java.util.Date date2=sdf.parse(fechadev);
				// devuelve <0 si actual es menor a devolver
				//devuelve 0 si son iguales
				//devuelve >0 si actual es mayor a devolver
				if (date1.compareTo(date2) < 0 || date1.compareTo(date2)==0 )
				{
		            System.out.println("puede devolver");
		            
		        	Sancion sanc=null;//asi reutilizo el mismo metodo 
					clp.update(l,sanc);
				
		        }else if (date1.compareTo(date2) > 0) {
		            System.out.println("sancionar");
		            //diferencia entre la fecha que habia que devolverla y hoy
		            int diasDif=(int)((date1.getTime()-date2.getTime())/86400000); 
		            		
		            
		            CtrlSocio cs=new CtrlSocio();
		            boolean est=false;
		            Socio s=l.getSocio();
		            cs.update(s, est);
		            
		            //obtengo tiempo de sancino
		            CtrlPoliticaSancion cps=new CtrlPoliticaSancion();
		            PoliticaSancion ps=cps.getOne(diasDif);
		            
		            
		            
		            
		            
		            
		            
		            
		        }  
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		     finally 
		     {
		    		request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
		     }
		
		
			
		}//fin if
		
		if(op.equals("Volver"))
		{
			request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
		}//fin if
	}

}
