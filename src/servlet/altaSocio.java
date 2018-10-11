package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.*;
import negocio.*;

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
		request.setAttribute("errorAltaS",null);
		String op=request.getParameter("op");
		if(op.equals("Registrar"))
		{
			String n=request.getParameter("nombre");
			String a=request.getParameter("apellido");
			String d=request.getParameter("domicilio");
			String e=request.getParameter("email");
			String t=request.getParameter("telefono");
			String dni=request.getParameter("dni");		
			CtrlSocio cs=new CtrlSocio();
			Socio  s=cs.getOne(dni);
			if(s==null)
			{
				Socio socio=new Socio(a,n,e,d,t,dni);
				cs.add(socio);
				int tipo=1; 
				Usuario u=new Usuario(dni,a,tipo);
				CtrlUsuario cu=new CtrlUsuario();
				cu.add(u,socio.getIdSocio());
				int nro=1;
				request.getSession().setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			else 
			{
				String msj = "Ya existe un socio con el dni "+dni;
				request.setAttribute("errorAltaS", msj);
				request.getRequestDispatcher("WEB-INF/lib/altaSocio.jsp").forward(request, response);
			}
		}
		
		if(op.equals("Cancelar"))
		{
			request.setAttribute("errorAltaS",null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}

}
