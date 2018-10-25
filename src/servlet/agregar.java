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
		request.setAttribute("error",null);
		String op = request.getParameter("op");	
		if (op.equals("Agregar")) 
		{
			int id = Integer.parseInt(request.getParameter("idEjemplar"));
			CtrlEjemplar ce = new CtrlEjemplar();
			Ejemplar e = ce.getOne(id); // veo si existe el id
			if (e == null)
			{
				String msj = "El id "+id+" es incorrecto";
				request.setAttribute("error", msj);
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession();
				Socio s = (Socio) session.getAttribute("socio");
				if (ce.existe(id,s) == true)// buscar si esta en otro prestamo de otra persona sin devolver aun
				{
					String msj = "El ejemplar "+id+" no esta disponible";
					request.setAttribute("error", msj);
					request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
				}
				else
				{
					CtrlLineaDePrestamo clp = new CtrlLineaDePrestamo();
					LineaDePrestamo rta = clp.getOne(s.getIdSocio(), id); // veo si ya tengo otros ejemplares de mismo libro
					if (rta == null) 
					{
						String msj = "Ya posee otros ejemplares del mismo libro prestados.";
						request.setAttribute("error", msj);
						request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
					}
					else
					{
						// que cree el prestamo la primera vez y las siguientes solo agregue
						CtrlPrestamo cp = new CtrlPrestamo();
						Prestamo app = (Prestamo) session.getAttribute("prestamo");
						if (app == null) 
						{
							Prestamo p = new Prestamo(s);
							cp.add(p); 
							session.setAttribute("prestamo", p);
						}
						Prestamo ap = (Prestamo) session.getAttribute("prestamo");
						LineaDePrestamo lp = new LineaDePrestamo(s, ap, e);
						boolean resultado = clp.buscarLinea(lp); // me fijo si la linea de prestamo ya existe
						if (resultado == true) 
						{
							String msj = "El ejemplar "+id+" ya esta en el prestamo";
							request.setAttribute("error", msj);
							request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
						}
						else
						{
							int c = (Integer) session.getAttribute("cantPosible"); // fijarse que no se supere la cantidad permitida de libros prestados
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
					}
				}
				
			} 
					
		}
		if (op.equals("Guardar")) 
		{
			request.setAttribute("errorDev",null);
			HttpSession session = request.getSession();
			int di = Integer.parseInt(request.getParameter("diasMaximoPrestamo"));
			int min=(Integer)session.getAttribute("dias");
			Prestamo pre = (Prestamo) session.getAttribute("prestamo");
			CtrlPrestamo cp=new CtrlPrestamo();
			if(di>min)
			{
				GregorianCalendar fecha = new GregorianCalendar();
				fecha.setTime(pre.getFechaPrestamo());
				fecha.add(Calendar.DATE, min);
				java.sql.Date sDate = convertUtilToSql(fecha.getTime());
				cp.update(pre, min,sDate);
				int nro=3;
				Socio s=(Socio)request.getSession().getAttribute("socio");
				String msj="Se informa que se ha registrado el prestamo nro "+pre.getIdPrestamo()+" para el socio "+s.getApellido()+" "+s.getNombre()+".\n El mismo es por "+ min+" dias y la fecha de devolucion es el "+sDate+".\n Atte. Biblioteca Rosario";
				Emailer.getInstance().send(s.getEmail(),"Confirmacion prestamo",msj);
				request.getSession().setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);	
			}
			else
			{
				GregorianCalendar fecha = new GregorianCalendar();
				fecha.setTime(pre.getFechaPrestamo());
				fecha.add(Calendar.DATE, di);
				java.sql.Date sDate = convertUtilToSql(fecha.getTime());
				cp.update(pre, di,sDate);
				int nro=4;
				Socio s=(Socio)request.getSession().getAttribute("socio");// obtengo el mail y lo pongo den el send
				String msj="Se informa que se ha registrado el prestamo nro "+pre.getIdPrestamo()+" para el socio "+s.getApellido()+" "+s.getNombre()+".\n El mismo es por "+ di+" dias y la fecha de devolucion es el "+sDate+".\n Atte. Biblioteca Rosario";
				Emailer.getInstance().send("antonellabj21@gmail.com","Confirmacion prestamo",msj);
				request.getSession().setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			session.setAttribute("prestamo",null);
			session.setAttribute("lineas",null);
		}
		
		if(op.equals("Cancelar"))
		{	
			request.setAttribute("errorDev",null);
			HttpSession session = request.getSession();
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