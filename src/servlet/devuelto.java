package servlet;

import java.io.IOException;
import java.sql.Date;
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
			
			GregorianCalendar fecha = new GregorianCalendar();
			java.util.Date d = new java.util.Date();
			Date fechaActual = new java.sql.Date(d.getTime());
			int dd=fechaActual.compareTo(l.getPrestamo().getFechaADevolver());
			System.out.println("en registrar");
		System.out.println(dd);
			
			//si la fecha a devolver es mayor a la actual, devuelve menos a 0, si son iguale devuelve 0. si la actual es mayor a la de devolver devuelve mayor a 0
		
			//clp.update(l);
			request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
			
		}//fin if
		
		if(op.equals("Volver"))
		{
			request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
		}//fin if
	}

}
