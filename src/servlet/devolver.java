package servlet;

import java.io.IOException;

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
public class devolver extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devolver()
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
		request.setAttribute("errorDev",null);
		String op=request.getParameter("opc");
		if(op.equals("Buscar"))
		{
			int id=Integer.parseInt(request.getParameter("idSocio"));
			CtrlSocio cs=new CtrlSocio();
			Socio s=cs.getOne(id);
			if(s == null)
			{
				String msj = "No existe el socio con el id "+id;
				request.setAttribute("errorDev", msj);
				request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);
			}
			else 
			{
				//ver si posee libros pendientes de devolucion
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				ArrayList<LineaDePrestamo> lineas=clp.getAll(id);
				if(lineas.isEmpty())
				{
					String msj = "El socio con id "+id+ " No posee libros pendientes de devolucion";
					request.setAttribute("errorDev", msj);
					request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);				
				}
				else
				{
					request.getSession().setAttribute("lineasD",lineas);
					request.getSession().setAttribute("socioD",s);
					request.getRequestDispatcher("/WEB-INF/lib/devolverRegistrar.jsp").forward(request, response);
				}
			}
		}
		if(op.equals("Volver"))
		{
			request.getSession().setAttribute("socioD",null);
			request.getSession().setAttribute("lineasD",null);
			request.setAttribute("errorDev",null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
