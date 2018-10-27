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
		if(op!=null)
		{
			if(op.equals("Volver"))
			{
				request.getRequestDispatcher("/menu.jsp").forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession();
				Usuario u=(Usuario)session.getAttribute("usuario");
				CtrlSocio cs=new CtrlSocio();
				Socio s=cs.getOne(u.getSocio().getIdSocio());
				request.setAttribute("socio",s);
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				ArrayList<LineaDePrestamo> lineas=clp.getAll(s.getIdSocio());
				request.setAttribute("lineas",lineas);
				request.getRequestDispatcher("WEB-INF/lib/pendiente.jsp").forward(request, response);	
			}
		}
	}

}
