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
 * Servlet implementation class estado
 */
@WebServlet("/estado")
public class estado extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public estado()
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
		request.setAttribute("errorEstInh",null);
		request.setAttribute("errorEstHab", null);
		String op=request.getParameter("op");
		if(op.equals("Inhabilitar"))
		{
			ArrayList<Socio> lista=new ArrayList<Socio>();
			CtrlSocio cs=new CtrlSocio();
			String[] lin=request.getParameterValues("chk");
			if(lin!=null)
			{
				for(int i=0;i<lin.length;i++)
				{
					Socio s=cs.getOne(Integer.parseInt(lin[i]));
					lista.add(s);
				}
				for(Socio ss:lista) 
				{
					cs.update(ss, false);
				}
				int nro=7;
				request.setAttribute("opc",nro);			
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			else 
			{
				String msj="No se selecciono ningun socio para inhabilitar";
				request.setAttribute("errorEstInh",msj);
				request.getRequestDispatcher("WEB-INF/lib/estado.jsp").forward(request, response);
			}
		}
		if(op.equals("Habilitar"))
		{
			ArrayList<Socio> lista=new ArrayList<Socio>();
			CtrlSocio cs=new CtrlSocio();
			String[] lin=request.getParameterValues("chk");
			if(lin!=null)
			{
				for(int i=0;i<lin.length;i++)
				{
					Socio s=cs.getOne(Integer.parseInt(lin[i]));
					lista.add(s);
				}
				for(Socio ss:lista) 
				{
					cs.update(ss, true);
				}
				int nro=8;
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			else
			{
				String msj="No se selecciono ningun socio para habilitar";
				request.setAttribute("errorEstHab",msj);
				request.getRequestDispatcher("WEB-INF/lib/estado.jsp").forward(request, response);
			}
		}
		if(op.equals("Volver"))
		{
			request.setAttribute("errorEstInh",null);
			request.setAttribute("errorEstHab",null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
