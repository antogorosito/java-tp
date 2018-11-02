package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.*;
import negocio.*;
import util.AppDataException;

/**
 * Servlet implementation class altaSocio
 */
@WebServlet({ "/altaSocio", "/altasocio" })
public class altaSocio extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaSocio() 
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
		String op=request.getParameter("op");
		if(op.equals("Registrar"))
		{
			try
			{
				String n=request.getParameter("nombre");
				String a=request.getParameter("apellido");
				String d=request.getParameter("domicilio");
				String e=request.getParameter("email");
				String t=request.getParameter("telefono");
				String dni=request.getParameter("dni");		
				CtrlSocio cs=new CtrlSocio();
				Socio  s=cs.getOne(dni);
				s=new Socio(a,n,e,d,t,dni);
				cs.add(s);
				int tipo=1; 
				Usuario u=new Usuario(dni,a,tipo);
				CtrlUsuario cu=new CtrlUsuario();
				cu.add(u,s.getIdSocio());
				int nro=1;
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("/WEB-INF/lib/altaSocio.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("/WEB-INF/lib/altaSocio.jsp").forward(request, response);
			}	
			
		}
		
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

}
