package servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import negocio.*;
import entidades.*;


/**
 * Servlet implementation class consultas
 */
@WebServlet("/consultas")
public class consultas extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultas()
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
		request.setAttribute("errorCon",null);
		String op=request.getParameter("op");
		if(op.equals("Buscar"))
		{
			String t=request.getParameter("titulo");	
			CtrlEjemplar ce=new CtrlEjemplar();
			ArrayList<Ejemplar> ejemplares=ce.buscar(t);
			request.setAttribute("titulo",t);
			if (ejemplares.isEmpty() == false)
			{	
				request.setAttribute("listaejemplares", ejemplares);	
				request.getRequestDispatcher("/consultas.jsp").forward(request, response);
			}
			else
			{
				String msj = "No existen libros con el titulo "+t;
				request.setAttribute("errorCon", msj);
				request.getRequestDispatcher("/consultas.jsp").forward(request, response);
			}
		}
		if(op.equals("Volver"))
		{
			request.setAttribute("errorCon",null);
			Usuario u=(Usuario)request.getSession().getAttribute("usuario");
			if(u!=null)
			{
				request.getRequestDispatcher("/menu.jsp").forward(request, response);
			}
			else 			
			{
				request.getRequestDispatcher("/index.html").forward(request, response);
			}	
		}
	}

}
