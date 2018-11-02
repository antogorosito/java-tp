package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.*;
import negocio.*;
import entidades.*;

/**
 * Servlet implementation class agregar
 */
@WebServlet("/agregar")
public class agregar extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregar()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String op = request.getParameter("op");
		HttpSession session = request.getSession();
		if (op.equals("Agregar")) 
		{
			try
			{
				int id = Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce = new CtrlEjemplar();
				Ejemplar e = ce.getOne(id);  //ver que existe
				Socio s = (Socio) session.getAttribute("socio");
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				LineaDePrestamo lll=clp.existe(id, s); //ver si esta en el prestamo de otra persona
				LineaDePrestamo rta = clp.getOne(s, id);	 // veo si ya tengo otros ejemplares de mismo libro
				//creo el prestamos por primera vez y en los siguientes solo agrego lineas
				CtrlPrestamo cp = new CtrlPrestamo();
				Prestamo ap = (Prestamo) session.getAttribute("prestamo"); 
				if (ap == null) 
				{
					Prestamo p = new Prestamo(s);
					cp.add(p); 
					session.setAttribute("prestamo", p);
				}
				ap = (Prestamo) session.getAttribute("prestamo");
				LineaDePrestamo lp = new LineaDePrestamo(ap, e);
				LineaDePrestamo li = clp.buscarLinea(lp); // ver si la linea de prestamo ya existe
				int c = (Integer) session.getAttribute("cantPosible"); //ver que no se supere la cantidad permitida de libros prestados
				if (c <= 0) 
				{
					String msj = "No se pueden agregar mas libros";
					request.setAttribute("error", msj);
				}
				else
				{
					clp.add(lp);
					c = c - 1;
					request.getSession().setAttribute("cantPosible", c);
				}
				ArrayList<LineaDePrestamo> lineas = clp.getAll(ap);
				request.getSession().setAttribute("lineas",lineas); 
				int dias = clp.minimoDias(ap);// buscar minima cantidad de dias en las lineas deprestamo
				request.getSession().setAttribute("dias", dias);
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}								
		}
		
		if (op.equals("Guardar")) 
		{
			int di = Integer.parseInt(request.getParameter("diasMaximoPrestamo"));
			int min=(Integer)session.getAttribute("dias");
			Prestamo pre = (Prestamo) session.getAttribute("prestamo");
			CtrlPrestamo cp=new CtrlPrestamo();
			if(di>min)
			{
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(pre.getFechaPrestamo());
				calendar.add(Calendar.DAY_OF_YEAR,min);
				java.sql.Date sDate = convertUtilToSql(calendar.getTime());
				
				cp.update(pre, min,sDate);
				int nro=3;
				Socio s=(Socio)request.getSession().getAttribute("socio");
				String msj="Se informa que se ha registrado el prestamo nro "+pre.getIdPrestamo()+" para el socio "+s.getApellido()+" "+s.getNombre()+".\n El mismo es por "+ min+" dias y la fecha de devolucion es el "+sDate+".\n Atte. Biblioteca Rosario";
				Emailer.getInstance().send(s.getEmail(),"Confirmacion prestamo",msj);
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);	
			}
			else
			{
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(pre.getFechaPrestamo());
				calendar.add(Calendar.DAY_OF_YEAR,di);
				java.sql.Date sDate = convertUtilToSql(calendar.getTime());
			
				cp.update(pre, di,sDate);
				int nro=4;
				Socio s=(Socio)request.getSession().getAttribute("socio");
				String msj="Se informa que se ha registrado el prestamo nro "+pre.getIdPrestamo()+" para el socio "+s.getApellido()+" "+s.getNombre()+".\n El mismo es por "+ di+" dias y la fecha de devolucion es el "+sDate+".\n Atte. Biblioteca Rosario";
				Emailer.getInstance().send(s.getEmail(),"Confirmacion prestamo",msj);
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			session.setAttribute("prestamo",null);
			session.setAttribute("lineas",null);
		}
		
		if(op.equals("Cancelar"))
		{	
			CtrlLineaDePrestamo clp= new CtrlLineaDePrestamo();
			ArrayList<LineaDePrestamo> li= (ArrayList<LineaDePrestamo>)session.getAttribute("lineas");
			if(li != null) 
			{
				for(LineaDePrestamo ll:li) 
				{
					clp.delete(ll);
				}
				CtrlPrestamo cp = new CtrlPrestamo();
				Prestamo ap = (Prestamo) session.getAttribute("prestamo");
				cp.delete(ap);
			}
			session.setAttribute("prestamo",null);
			session.setAttribute("lineas",null);
			request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
		}

	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) 
	{
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	

	
}