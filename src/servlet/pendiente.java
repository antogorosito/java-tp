package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;
import util.AppDataException;
/**
 * Servlet implementation class pendiente
 */
@WebServlet("/pendiente")
public class pendiente extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pendiente()
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
		try
		{
			Usuario u=(Usuario)request.getSession().getAttribute("usuario");
			CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
			ArrayList<LineaDePrestamo> lineas=clp.getAll(u.getSocio().getIdSocio());
			request.setAttribute("lineas",lineas);
			request.getRequestDispatcher("WEB-INF/lib/pendiente.jsp").forward(request, response);	
		}
		catch(AppDataException ape)
		{
			request.setAttribute("error",ape.getMessage());
			request.getRequestDispatcher("WEB-INF/lib/pendiente.jsp").forward(request, response);
		}
		catch (Exception e) 
		{
			request.setAttribute("error",e.getMessage());
			request.getRequestDispatcher("WEB-INF/lib/pendiente.jsp").forward(request, response);
		}	
	}

}
