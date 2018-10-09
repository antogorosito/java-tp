package servlet;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class devueltoUnoRegistrar
 */
@WebServlet("/devueltoUnoRegistrar")
public class devueltoUnoRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devueltoUnoRegistrar() {
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
		           	
					clp.update(l);
					request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
				
		        }else if (date1.compareTo(date2) > 0) {
		         
		            //diferencia entre la fecha que habia que devolverla y hoy
		            int diasDif=(int)((date1.getTime()-date2.getTime())/86400000); 
		            		
		            
		            CtrlSocio cs=new CtrlSocio();
		            boolean est=false;//inhabilito
		            Socio s=l.getSocio();
		            cs.update(s, est);
		            
		            //obtengo tiempo de sancino
		            CtrlPoliticaSancion cps=new CtrlPoliticaSancion();
		            PoliticaSancion ps=cps.getOne(diasDif);
		            if(ps==null) // por si son mas dias de atraso que los que tienen la bd, lo sanciono por el maximo de dias cargado.
		            {
		            	ps=cps.getMax();
		            }
		            CtrlSancion css=new CtrlSancion();
		            Sancion sa=css.getOne(l);
		            if(sa==null)
		            {
		            sa=new Sancion(ps.getDiasDeSancion(),l.getSocio());
		            css.add(sa);
		            }
		            
		            clp.update(l,sa);
		            request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
		    
		        }  
			} catch (ParseException e) {
				
				e.printStackTrace();
			}		

		}
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
		}
	}

}
