package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class altaEjemplar
 */
@WebServlet("/altaEjemplar")
public class altaEjemplar extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaEjemplar() 
    {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession session= request.getSession();
		String op=request.getParameter("op");
		if(op.equals("Buscar"))
		{
			try 
			{
				String i=request.getParameter("ISBN");
				CtrlLibro cl= new CtrlLibro();
				Libro  l=cl.getOne(i);
				if(l==null) 
				{
					String msj = "No existe un libro con el ISBN "+i;
					request.setAttribute("error", msj);
					request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);	
				} 
				else 
				{
					request.getSession().setAttribute("Libro",l);//para que cdo agrego un ejemplar sin libro me llene el input
					request.getSession().setAttribute("L", l);
					request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
				}
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
			}		
		} 
		
		if(op.equals("Guardar")) 
		{	
			try {
			int id=Integer.parseInt(request.getParameter("idEjemplar"));
			CtrlEjemplar ce= new CtrlEjemplar();
			Ejemplar e=ce.getEjemplar(id);
			if(e!=null) 
			{
				String msj = "Ya existe un ejemplar con el id "+id;
				request.setAttribute("error", msj);
				request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);	
			} 
			else
			{
			    Libro l = (Libro) session.getAttribute("L");
				ce.add(id,l);
				session.setAttribute("L", null);
				session.setAttribute("Libro", null);
				int nro=2;
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);	
			}
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}		
		}
		if(op.equals("Cancelar")) 
		{
			session.setAttribute("L", null);
			session.setAttribute("Libro", null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
		if(op.equals("Agregar mas")) 
		{
			try 
			{
				int id=Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce= new CtrlEjemplar();
				Ejemplar e=ce.getEjemplar(id);
				if(e!=null) 
				{
					String msj = "Ya existe un ejemplar con el id "+id;
					request.setAttribute("error", msj);
					request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
				}
				else 
				{
					Libro l = (Libro) session.getAttribute("L");
					ce.add(id,l);
					request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
				}
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}	
		}
	}

}
