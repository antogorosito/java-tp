package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menu() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String op = request.getParameter("op");	
		if(op.equals("altaSocio"))
		{
			request.getRequestDispatcher("WEB-INF/lib/altaSocio.jsp").forward(request, response);
		}
		if(op.equals("consulta"))
		{
			request.getRequestDispatcher("consultas.jsp").forward(request, response);	
		}
		if(op.equals("altaLibro")) 
		{
			request.getRequestDispatcher("WEB-INF/lib/altaLibro.jsp").forward(request, response);
		}
		if(op.equals("prestamo"))
		{
			request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
		}
		if(op.equals("devueltoUno")) 
		{
			request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
		}
		if(op.equals("devolver"))
		{
			request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);
		}
		if(op.equals("informes")) 
		{
			request.getRequestDispatcher("WEB-INF/lib/informes.jsp").forward(request, response);
		}
		if(op.equals("estado"))
		{
			request.getRequestDispatcher("WEB-INF/lib/estado.jsp").forward(request, response);
		}
		if(op.equals("altaEjemplar"))
		{
			request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
		}
		if(op.equals("pendiente"))
		{
			request.getRequestDispatcher("WEB-INF/lib/pendiente.jsp").forward(request, response);
		}
		if(op.equals("contraseña"))
		{
			request.getRequestDispatcher("WEB-INF/lib/contraseña.jsp").forward(request, response);
		}
	}

}
