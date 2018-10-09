package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class altaEjemplar
 */
@WebServlet("/altaEjemplar")
public class altaEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaEjemplar() {
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
		String op=request.getParameter("op");
		if(op.equals("Buscar")) {
			String i=request.getParameter("ISBN");
			
			CtrlLibro cl= new CtrlLibro();
			Libro  l=cl.getOne(i);
			if(l==null) {
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('No existe un libro con ese  ISBN');");
				out.println("location='altaEjemplar.jsp';");
				out.println("</script>");
			} else {
				request.getSession().setAttribute("L", l);
				request.getRequestDispatcher("/altaEjemplar.jsp").forward(request, response);
			}
		} 
		if(op.equals("Guardar")) {
			int id=Integer.parseInt(request.getParameter("idEjemplar"));
			
			CtrlEjemplar ce= new CtrlEjemplar();
			Ejemplar e=ce.getEjemplar(id);
			
			if(e!=null) {
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Ya existe id de ejemplar');");
				out.println("location='altaEjemplar.jsp';");
				out.println("</script>");
			} else {
				HttpSession session = request.getSession();
			    Libro l = (Libro) session.getAttribute("L");
				ce.add(id,l);
				
				session.setAttribute("L", null);
				session.setAttribute("Libro", null);
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Nuevo ejemplar registrado');");
				out.println("location='altaEjemplar.jsp';");
				out.println("</script>");
				
			}
			
		}
		if(op.equals("Cancelar")) {
			HttpSession session= request.getSession();
			session.setAttribute("L", null);
			session.setAttribute("Libro", null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		if(op.equals("Agregar mas")) {
			
int id=Integer.parseInt(request.getParameter("idEjemplar"));
			
			CtrlEjemplar ce= new CtrlEjemplar();
			Ejemplar e=ce.getEjemplar(id);
			
			if(e!=null) {
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Ya existe id de ejemplar');");
				out.println("location='altaEjemplar.jsp';");
				out.println("</script>");
			} else {
				HttpSession session = request.getSession();
			    Libro l = (Libro) session.getAttribute("L");
				ce.add(id,l);
				
			
			
			request.getRequestDispatcher("/altaEjemplar.jsp").forward(request, response);
			}
		}
	}

}
