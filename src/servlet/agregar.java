package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import negocio.*;
import entidades.*;

/**
 * Servlet implementation class agregar
 */
@WebServlet("/agregar")
public class agregar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");	
	
		if (op.equals("Agregar")) {
			
			if (request.getParameter("idEjemplar") == "") {
				
			PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Id vacio');");
				out.println("location='WEB-INF/lib/agregar.jsp';");
				out.println("</script>");
			} else {
				int id = Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce = new CtrlEjemplar();
				Ejemplar e = ce.getOne(id); // veo si existe el id
				if (e == null) {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Id ejemplar incorrecto');");
					out.println("location='WEB-INF/lib/agregar.jsp';");
					out.println("</script>");
				} else {
					HttpSession session = request.getSession();
					Socio s = (Socio) session.getAttribute("socio");
					if (ce.existe(id,s) == true)// buscar si esta en otro prestamo de otra persona sin devolver aun
					{
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('ejemplar no disponible');");
						out.println("location='WEB-INF/lib/agregar.jsp';");
						out.println("</script>");
					} else {
						CtrlLineaDePrestamo clp = new CtrlLineaDePrestamo();
						
						boolean rta = clp.getOne(s.getIdSocio(), id); // veo si ya tengo otros ejemplares de mismo libro
						if (rta == true) 
						{
							PrintWriter out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Posee otros ejemplares del mismo libro ya prestados');");
							out.println("location='WEB-INF/lib/agregar.jsp';");
							out.println("</script>");
						} else {
							// que cree el prestamo la primera vez y las siguientes solo agregue
							CtrlPrestamo cp = new CtrlPrestamo();
							Prestamo app = (Prestamo) session.getAttribute("prestamo");
							if (app == null) {
								/* creo el prestamo */
								Prestamo p = new Prestamo(s);
								cp.add(p); // agrego el prestamo a la bd

								session.setAttribute("prestamo", p);

							}
							/*
							 * debo buscar si el libro ya esta en el prestamo y si no esta dsp agregar la
							 * linea
							 */

							// obtengo el prestamo, ya que antes no lo tenia con id.(cdo entro mas de una
							// vez)

							Prestamo ap = (Prestamo) session.getAttribute("prestamo");
							

							LineaDePrestamo lp = new LineaDePrestamo(s, ap, e);
							boolean resultado = clp.buscarLinea(lp); // me fijo si la linea de prestamo ya existe
							if (resultado == true) 
							{
								PrintWriter out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
								out.println("alert('ya esta en el prestamo');");
								out.println("location='WEB-INF/lib/agregar.jsp';");
								out.println("</script>");
							} else {
								int c = (Integer) session.getAttribute("cantPosible"); // fijarse que no se supere la
																						// cantidad permitida de libros
																						// prestados
								if (c <= 0) {
									String msj = "No se pueden agregar mas libros";
									request.getSession().setAttribute("error", msj);
								} else {
									clp.add(lp); // agrego la linea de prestamo a la bd
									c = c - 1;
									request.getSession().setAttribute("cantPosible", c);
								}
								ArrayList<LineaDePrestamo> lineas = clp.getAll(ap);// devolver TODAS LAS LINEAS DEL PRESTAMO
								//request.setAttribute("lineas", lineas);
								request.getSession().setAttribute("lineas",lineas); // para que me devuelva la lista aun con los carteles uso el session antes use la linea anterior
								int dias = clp.minimoDias(ap);// buscar minima cantidad de dias en las lineas deprestamo
								request.getSession().setAttribute("dias", dias);
								request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
							}
						}
					}
				}
			} //
		
			
		}

		if (op.equals("Guardar")) {
			HttpSession session = request.getSession();
			int di = Integer.parseInt(request.getParameter("diasMaximoPrestamo"));
			int min=(Integer)session.getAttribute("dias");
			Prestamo pre = (Prestamo) session.getAttribute("prestamo");
			
			CtrlPrestamo cp=new CtrlPrestamo();
			if(di>min)
			{
				cp.update(pre, min);

			PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Se ha guardado el prestamo por el minimo de dias establecido');");
				out.println("location='WEB-INF/lib/menu.jsp';");
				out.println("</script>");
			}
			else
			{
				cp.update(pre, di);

				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Se ha guardado el prestamo con los dias ingresados');");
				out.println("location='WEB-INF/lib/menu.jsp';");
				out.println("</script>");
			}
			session.setAttribute("prestamo",null);
			session.setAttribute("lineas",null);
	
		}
		if(op.equals("Cancelar"))
		{	
			HttpSession session = request.getSession();
			CtrlLineaDePrestamo clp= new CtrlLineaDePrestamo();
			ArrayList<LineaDePrestamo> li= (ArrayList<LineaDePrestamo>)session.getAttribute("lineas");
			for(LineaDePrestamo ll:li) 
			{
				clp.delete(ll);
			}
			CtrlPrestamo cp = new CtrlPrestamo();
			Prestamo ap = (Prestamo) session.getAttribute("prestamo");
			cp.delete(ap);
			session.setAttribute("prestamo",null);
			session.setAttribute("lineas",null);
			request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
		}

	}

	
}