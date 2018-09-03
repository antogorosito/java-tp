package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.*;
import entidades.*;

/**
 * Servlet implementation class prestamos
 */
@WebServlet("/prestamos")
public class prestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("idSocio"));
		CtrlSocio cs=new CtrlSocio();
		Socio s=cs.getOne(id);
		if(s == null)
		{
			PrintWriter out= response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No existe el socio con ese id');");
			out.println("location='prestamos.jsp';");
			out.println("</script>");
		}
		else 
		{
			if(s.getEstado()==false)
			{
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Socio inhabilitado o sancionado');");
				out.println("location='prestamos.jsp';");
				out.println("</script>");
			}
			else
			{
				//buscar cantidad de prestamos de los socios para saber lo del tope
				CtrlPrestamo cp=new CtrlPrestamo();
				int cant=cp.obtenerCantidad(id);
				CtrlPoliticaPrestamo cpp=new CtrlPoliticaPrestamo();
				PoliticaPrestamo pp=cpp.getOne();
				if(pp.getCantMaxLibrosPend()<=cant)
				{
					PrintWriter out= response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Ya saco el tope de libros permitidos');");
					out.println("location='prestamos.jsp';");
					out.println("</script>");
				}
				else
				{
					request.getSession().setAttribute("socio",s);
					request.getRequestDispatcher("/prestamos.jsp").forward(request, response);
				}
			}
			
		}
		
	}

}
