package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Socio;
import negocio.CtrlSocio;
import util.AppDataException;

/**
 * Servlet implementation class estados
 */
@WebServlet("/estados")
public class estados extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public estados()
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
		HttpSession session = request.getSession();
		String op=request.getParameter("op");
		if(op.equals("Inhabilitar"))
		{
			try 
			{
				CtrlSocio cs=new CtrlSocio();
				ArrayList<Socio> lista=(ArrayList<Socio>)session.getAttribute("sociosInhabilitar");
				for(Socio ss:lista) 
				{
					cs.update(ss, false);
				}
				int nro=6;
				request.setAttribute("opc",nro);			
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
		}

		
		if(op.equals("Habilitar"))
		{
			try
			{
				CtrlSocio cs=new CtrlSocio();
				ArrayList<Socio> listaH=(ArrayList<Socio>)session.getAttribute("sociosHabilitar");
				for(Socio ss:listaH) 
				{
					cs.update(ss, true);
				}
				int nro=7;
				request.setAttribute("opc",nro);
				request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
			}
			catch(AppDataException ape)
			{
				request.setAttribute("error",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
			}
			
		}

		if(op.equals("Volver"))
		{
			request.getSession().setAttribute("sociosHabilitar",null);
			request.getSession().setAttribute("sociosInhabilitar",null);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
