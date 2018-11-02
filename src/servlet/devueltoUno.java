package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.*;
import util.AppDataException;
import entidades.*;

/**
 * Servlet implementation class devueltoUno
 */
@WebServlet("/devueltoUno")
public class devueltoUno extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devueltoUno()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String op = request.getParameter("opc");
		if(op.equals("Agregar"))
		{
			try 
			{
				int id = Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce = new CtrlEjemplar();
				Ejemplar e = ce.getOne(id); // veo si existe el id
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				LineaDePrestamo lp=clp.getOne(id);//me fijo si esta pendiente de devolucion
				session.setAttribute("lineaPre",lp);
				request.getRequestDispatcher("WEB-INF/lib/devueltoUnoRegistrar.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
			}
		}

		if(op.equals("Volver"))
		{
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

}


