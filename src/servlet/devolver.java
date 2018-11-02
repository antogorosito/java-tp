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
import util.AppDataException;

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
		String op=request.getParameter("opc");
		if(op.equals("Buscar"))
		{
			try
			{
				int id=Integer.parseInt(request.getParameter("idSocio"));
				CtrlSocio cs=new CtrlSocio();
				Socio s=cs.getOne(id);
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				ArrayList<LineaDePrestamo> lineas=clp.getAll(id);
				request.getSession().setAttribute("lineasD",lineas);
				request.getSession().setAttribute("socioD",s);
				request.getRequestDispatcher("/WEB-INF/lib/devolverRegistrar.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);
			}	
		}
		
		if(op.equals("Volver"))
		{
			request.getSession().setAttribute("socioD",null);
			request.getSession().setAttribute("lineasD",null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
