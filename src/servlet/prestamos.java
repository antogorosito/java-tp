package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.*;
import util.AppDataException;
import entidades.*;

/**
 * Servlet implementation class prestamos
 */
@WebServlet("/prestamos")
public class prestamos extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prestamos() 
    {
        super();
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
		if(op.equals("Buscar"))
		{
			try
			{
				int id=Integer.parseInt(request.getParameter("idSocio"));
				CtrlSocio cs=new CtrlSocio();
				Socio s=cs.getOne(id);
				if(s.getEstado()==false)
				{
					AppDataException ape = new AppDataException("El socio esta inhabilitado");
					throw ape;
				}
				else
				{
					CtrlPrestamo cp=new CtrlPrestamo();
					int cant=cp.obtenerCantidad(id);
					CtrlPoliticaPrestamo cpp=new CtrlPoliticaPrestamo();
					PoliticaPrestamo pp=cpp.getOne();
					if(pp.getCantMaxLibrosPend()<=cant)
					{
						AppDataException ape = new AppDataException("El socio ya saco el tope de libros");
						throw ape;
					}
					else
					{		
						int c=pp.getCantMaxLibrosPend()-cant;
						request.getSession().setAttribute("cantPosible",c);
						request.getSession().setAttribute("socio",s);				
						request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
					}		
				}
			}
			catch(AppDataException ape)
			{
				request.setAttribute("errorPre",ape.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
			}
			catch (Exception e) 
			{
				request.setAttribute("errorPre",e.getMessage());
				request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
			}						
		}
		
		if(op.equals("Volver"))
		{
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
