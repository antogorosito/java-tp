package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.*;
import entidades.*;

/**
 * Servlet implementation class prestamos
 */
@WebServlet("/prestamos")
public class prestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("errorPre",null);
		int id=Integer.parseInt(request.getParameter("idSocio"));
		CtrlSocio cs=new CtrlSocio();
		Socio s=cs.getOne(id);
		if(s == null)
		{
			String msj = "No existe el socio con el id "+id;
			request.getSession().setAttribute("errorPre", msj);
			request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
		
		}
		else 
		{
			if(s.getEstado()==false)
			{
				String msj = "El socio "+id+" esta inhabilitado o sancionado";
				request.getSession().setAttribute("errorPre", msj);
				request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
			
			}
			else
			{
				//buscar cantidad de prestamos de los socios para saber lo del tope
				CtrlPrestamo cp=new CtrlPrestamo();
				int cant=cp.obtenerCantidad(id);
				
				CtrlPoliticaPrestamo cpp=new CtrlPoliticaPrestamo();
				PoliticaPrestamo pp=cpp.getOne();// cantidad maxima de libros prestados posibles
				if(pp.getCantMaxLibrosPend()<=cant)
				{
					String msj = "El socio "+id+" ya saco el tope de libros permitidos";
					request.getSession().setAttribute("errorPre", msj);
					request.getRequestDispatcher("WEB-INF/lib/prestamos.jsp").forward(request, response);
		
				}
				else
				{		
					request.getSession().setAttribute("errorPre",null);
					int c=pp.getCantMaxLibrosPend()-cant;
					request.getSession().setAttribute("cantPosible",c);
					request.getSession().setAttribute("socio",s);				
					request.getRequestDispatcher("WEB-INF/lib/agregar.jsp").forward(request, response);
				}
			}
			
		}
		
	}

}
