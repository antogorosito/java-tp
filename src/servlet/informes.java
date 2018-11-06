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
 * Servlet implementation class informes
 */
@WebServlet("/informes")
public class informes extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public informes() 
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
		String op=request.getParameter("op");
		if(op.equals("informe1"))
		{
			try 
			{
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				ArrayList<LineaDePrestamo> lineas=clp.getAllPendiente();
				request.setAttribute("listaPendiente",lineas);
				request.getRequestDispatcher("WEB-INF/lib/informe1.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/informe1.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/informe1.jsp").forward(request, response);
			}
		}
		if(op.equals("informe2")) 
		{
			CtrlSocio cs=new CtrlSocio();
			ArrayList<Socio> sociosI = cs.getAllInhabilitados();
			request.setAttribute("listaInhabilitados",sociosI);
			request.getRequestDispatcher("WEB-INF/lib/informe2.jsp").forward(request, response);
				
		}
		if(op.equals("Volver"))
		{
			request.getRequestDispatcher("WEB-INF/lib/informes.jsp").forward(request, response);
		}
		if(op.equals("VolverI"))
		{
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

}
