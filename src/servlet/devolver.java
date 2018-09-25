package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class devolver
 */
@WebServlet("/devolver")
public class devolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devolver() {
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
		String op=request.getParameter("opc");
		if(op.equals("Buscar"))
		{
			int id=Integer.parseInt(request.getParameter("idSocio"));
			CtrlSocio cs=new CtrlSocio();
			Socio s=cs.getOne(id);
			if(s == null)
			{
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('No existe el socio con ese id');");
				out.println("location='devolver.jsp';");
				out.println("</script>");
			}
			else 
			{
				//ver si posee libros pendientes de devolucion
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				ArrayList<LineaDePrestamo> lineas=clp.getAll(id);
				if(lineas==null)
				{

					PrintWriter out= response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No posee libros pendientes de devolucion');");
					out.println("location='devolver.jsp';");
					out.println("</script>");
				}
				else
				{
					request.setAttribute("lineas",lineas);
					request.setAttribute("socio",s);
					request.getRequestDispatcher("/devolverRegistrar.jsp").forward(request, response);
				}
				
			}
		}
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
